package es.mrmoustard.tmdb.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.architectcoders.films.FakeLocalMoviesDataSourceImpl
import com.architectcoders.films.FakeLocationDeviceDataSourceImpl
import com.architectcoders.films.FakeMoviesRemoteDataSourceImpl
import es.mrmoustard.tmdb.common.getOrAwaitValue
import es.mrmoustard.tmdb.data.repository.LocationRepository
import es.mrmoustard.tmdb.data.repository.MoviesRepository
import es.mrmoustard.tmdb.domain.usecases.GetCountryCodeUseCase
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelIntegrationTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock private lateinit var moviesRepository: MoviesRepository
    @Mock private lateinit var locationRepository: LocationRepository

    private lateinit var getTopRatedUseCase: GetTopRatedUseCase
    private lateinit var getCountryCodeUseCase: GetCountryCodeUseCase
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        moviesRepository = MoviesRepository(
            remote = FakeMoviesRemoteDataSourceImpl(),
            local = FakeLocalMoviesDataSourceImpl()
        )
        locationRepository = LocationRepository(locationDevice = FakeLocationDeviceDataSourceImpl())

        getTopRatedUseCase = GetTopRatedUseCase( repository = moviesRepository)
        getCountryCodeUseCase = GetCountryCodeUseCase( repository = locationRepository)

        viewModel = HomeViewModel(
            mainDispatcher = Dispatchers.Unconfined,
            ioDispatcher = Dispatchers.Unconfined,
            getTopRatedUseCase = getTopRatedUseCase,
            getCountryCodeUseCase = getCountryCodeUseCase
        )
    }

    @Test
    fun `when top rated is called, then content is received`() {
        viewModel.getTopRated()

        Assert.assertTrue(viewModel.model.getOrAwaitValue() is HomeUiModel.Content)
    }

    @Test
    fun `when top rated is called, then filled content is received`() {
        viewModel.getTopRated()

        Assert.assertEquals(
            20,
            (viewModel.model.getOrAwaitValue() as HomeUiModel.Content).movies.size
        )
    }

    @Test
    fun `when top rated is called, then title from the first movie is the right one`() {
        viewModel.getTopRated()

        Assert.assertEquals(
            "Me contro Te: Il film - La vendetta del Signor S",
            (viewModel.model.getOrAwaitValue() as HomeUiModel.Content).movies[0].title
        )
    }
}
