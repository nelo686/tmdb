package es.mrmoustard.tmdb.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.left
import arrow.core.right
import com.nhaarman.mockitokotlin2.whenever
import es.mrmoustard.tmdb.common.getOrAwaitValue
import es.mrmoustard.tmdb.domain.entities.Location
import es.mrmoustard.tmdb.domain.entities.Movie
import es.mrmoustard.tmdb.domain.entities.TopRatedWrapper
import es.mrmoustard.tmdb.domain.errors.DomainError
import es.mrmoustard.tmdb.domain.usecases.GetCountryCodeUseCase
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getTopRatedUseCase: GetTopRatedUseCase

    @Mock
    private lateinit var getCountryCodeUseCase: GetCountryCodeUseCase
    private lateinit var viewModel: HomeViewModel

    private val region = "ES"
    private val page = 1

    @Before
    fun setUp() {
        viewModel = HomeViewModel(
            mainDispatcher = Dispatchers.Unconfined,
            ioDispatcher = Dispatchers.Unconfined,
            getTopRatedUseCase = getTopRatedUseCase,
            getCountryCodeUseCase = getCountryCodeUseCase
        )

        runBlocking {
            whenever(getCountryCodeUseCase.execute()).thenReturn(Location(countryCode = region).right())
        }
    }

    @Test
    fun `given repository default error response, when top rated is called, then error is received`() {
        //Given
        runBlocking {
            whenever(getTopRatedUseCase.execute(page = page, region = region))
                .thenReturn(DomainError.DefaultError(message = "default error").left())
        }

        //When
        viewModel.getTopRated()

        //Then
        Assert.assertTrue(viewModel.model.getOrAwaitValue() is HomeUiModel.ErrorResponse)
    }

    @Test
    fun `given repository unauthorized error response, when top rated is called, then error is received`() {
        //Given
        runBlocking {
            whenever(getTopRatedUseCase.execute(page = page, region = region))
                .thenReturn(DomainError.UnauthorizedError.left())
        }

        //when
        viewModel.getTopRated()

        //Then
        Assert.assertTrue(viewModel.model.getOrAwaitValue() is HomeUiModel.ErrorResponse)
    }

    @Test
    fun `given repository success response, when top rated is called, then content is received`() {
        //Given
        runBlocking {
            whenever(getTopRatedUseCase.execute(page = page, region = region))
                .thenReturn(
                    TopRatedWrapper(
                        page = page,
                        results = listOf(),
                        totalPages = 0,
                        totalResults = 0
                    ).right()
                )
        }

        //When
        viewModel.getTopRated()

        //Then
        Assert.assertTrue(viewModel.model.getOrAwaitValue() is HomeUiModel.Content)
    }

    @Test
    fun `given repository empty response, when top rated is called, then empty content is received`() {
        runBlocking {
            whenever(getTopRatedUseCase.execute(page = page, region = region))
                .thenReturn(
                    TopRatedWrapper(
                        page = page,
                        results = listOf(),
                        totalPages = 0,
                        totalResults = 0
                    ).right()
                )
        }

        viewModel.getTopRated()

        Assert.assertEquals(
            0,
            (viewModel.model.getOrAwaitValue() as HomeUiModel.Content).movies.size
        )
    }

    @Test
    fun `given repository with films response, when top rated is called, then filled content is received`() {
        runBlocking {
            whenever(getTopRatedUseCase.execute(page = page, region = region))
                .thenReturn(
                    TopRatedWrapper(
                        page = page,
                        results = listOf(Movie(), Movie(), Movie(), Movie()),
                        totalPages = 0,
                        totalResults = 0
                    ).right()
                )
        }

        viewModel.getTopRated()

        Assert.assertEquals(
            4,
            (viewModel.model.getOrAwaitValue() as HomeUiModel.Content).movies.size
        )
    }

    @Test
    fun `when film is selected, then detail transition is performed`() {
        viewModel.onMovieClicked(movieId = 0)
        Assert.assertNotNull(viewModel.detailTransition.getOrAwaitValue().getContentIfNotHandled())
    }

    @Test
    fun `when film is selected, then detail transition is performed with the right id`() {
        viewModel.onMovieClicked(movieId = 0)

        Assert.assertEquals(
            0,
            viewModel.detailTransition.getOrAwaitValue().getContentIfNotHandled()
        )
    }

    @Test
    fun `when viewmodel is created, permission transition is performed`() {
        Assert.assertEquals(
            true,
            viewModel.permissionTransition.getOrAwaitValue().getContentIfNotHandled()
        )
    }
}
