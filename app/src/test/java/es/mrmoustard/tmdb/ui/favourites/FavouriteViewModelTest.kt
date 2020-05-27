package es.mrmoustard.tmdb.ui.favourites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import es.mrmoustard.tmdb.common.StubData
import es.mrmoustard.tmdb.common.getOrAwaitValue
import es.mrmoustard.tmdb.domain.usecases.GetFavouriteMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavouriteViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var usecase: GetFavouriteMoviesUseCase

    private lateinit var viewModel: FavouriteViewModel

    @Before
    fun setUp() {
        viewModel = FavouriteViewModel(
            mainDispatcher = Dispatchers.Unconfined,
            ioDispatcher = Dispatchers.Unconfined,
            useCase = usecase
        )
    }

    @Test
    fun `when list is empty, then show empty message`() {
        runBlocking {
            //Given
            whenever(usecase.execute()).thenReturn(emptyList())

            // When
            viewModel.getFavourites()

            // Then
            viewModel.model.observeForever {
                val expected = FavouriteUiModel.EmptyState
                assertEquals(expected, it)
            }
        }
    }

    @Test
    fun `when there are favourite movies, show them`() {
        runBlocking {
            //Given
            whenever(usecase.execute()).thenReturn(StubData.movies)

            // When
            viewModel.getFavourites()

            //Then
            val expected = FavouriteUiModel.Content(movies = StubData.movies)
            val actual = viewModel.model.value
            actual?.let {
                assertEquals(expected.movies[0].id, (it as FavouriteUiModel.Content).movies[0].id)
                assertEquals(expected.movies[0].title, it.movies[0].title)
                assertEquals(expected.movies[0].backdropPath, it.movies[0].backdropPath)
            }
        }
    }

    @Test
    fun `when a movie is selected, then detail transition is performed`() {
        //When
        viewModel.onMovieClicked(movieId = 0)

        //Then
        assertNotNull(viewModel.detailTransition.getOrAwaitValue().getContentIfNotHandled())
    }

    @Test
    fun `when a movie is selected, then detail transition is performed with the right id`() {
        //When
        viewModel.onMovieClicked(movieId = 0)

        //then
        assertEquals(
            0,
            viewModel.detailTransition.getOrAwaitValue().getContentIfNotHandled()
        )
    }
}