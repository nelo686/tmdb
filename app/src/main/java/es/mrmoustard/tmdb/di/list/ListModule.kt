package es.mrmoustard.tmdb.di.list

import dagger.Module
import dagger.Provides
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase
import es.mrmoustard.tmdb.ui.list.ListViewModel

@Module
class ListModule {

    @Provides
    fun provideListViewModel(useCase: GetTopRatedUseCase): ListViewModel =
        ListViewModel(useCase = useCase)
}
