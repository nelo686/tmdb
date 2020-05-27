package es.mrmoustard.tmdb.ui.watchlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import es.mrmoustard.tmdb.common.StubData
import es.mrmoustard.tmdb.common.getOrAwaitValue
import es.mrmoustard.tmdb.domain.usecases.GetMoviesToWatchUseCase
import junit.framework.Assert.assertEquals
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
class WatchListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: GetMoviesToWatchUseCase

    private lateinit var viewModel: WatchListViewModel

    @Before
    fun setUp() {
        viewModel = WatchListViewModel(
            mainDispatcher = Dispatchers.Unconfined,
            ioDispatcher = Dispatchers.Unconfined,
            useCase = useCase
        )
    }

    @Test
    fun `when list is empty, then show empty message`() {
        //Given
        runBlocking {
            whenever(useCase.execute()).thenReturn(emptyList())
        }

        //When
        viewModel.getMoviesToWatch()

        //Then
        val expected = WatchListUiModel.EmptyState
        val actual = viewModel.model.value
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `when there are movies marked to watch, show them`() {
        //Given
        runBlocking {
            whenever(useCase.execute()).thenReturn(StubData.movies)
        }

        //When
        viewModel.getMoviesToWatch()

        //Then
        val expected = WatchListUiModel.Content(StubData.movies)
        val actual = viewModel.model.value
        actual?.let {
            assertEquals(expected.movies[0].id, (it as WatchListUiModel.Content).movies[0].id)
            assertEquals(expected.movies[0].title, it.movies[0].title)
            assertEquals(expected.movies[0].backdropPath, it.movies[0].backdropPath)
        }
    }

    @Test
    fun `when movie is selected, then detail transition is performed`() {
        viewModel.onMovieClicked(movieId = 0)

        Assert.assertTrue(
            viewModel.detailTransition.getOrAwaitValue().getContentIfNotHandled() != null
        )
    }

    @Test
    fun `when movie is selected, then detail transition is performed with the right id`() {
        viewModel.onMovieClicked(movieId = 0)

        Assert.assertEquals(
            0,
            viewModel.detailTransition.getOrAwaitValue().getContentIfNotHandled()
        )
    }
}