package es.mrmoustard.tmdb.ui.common

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface Scope : CoroutineScope {

    var job: Job
    var mainDispatcher: CoroutineDispatcher
    var ioDispatcher: CoroutineDispatcher
    override val coroutineContext: CoroutineContext
        get() = mainDispatcher + job

    fun initScope() {
        job = SupervisorJob()
    }

    fun cancelScope() {
        job.cancel()
    }

    class Impl(
        override var mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
        override var ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) : Scope {
        override lateinit var job: Job
    }
}
