package es.mrmoustard.tmdb.ui.list

import androidx.lifecycle.ViewModel
import es.mrmoustard.tmdb.domain.usecases.GetTopRatedUseCase
import es.mrmoustard.tmdb.ui.Scope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val useCase: GetTopRatedUseCase
) : ViewModel(), Scope by Scope.Impl() {

    init {
        initScope()
    }

    fun getTopRated(page: Int = 1, region: String = "") {
        launch {
            withContext(ioDispatcher) {
                useCase.execute(page = page, region = region)
            }.fold({
                val a = 0
            }, {
                val a = 0
            })
        }
    }

    override fun onCleared() {
        cancelScope()
        super.onCleared()
    }
}
