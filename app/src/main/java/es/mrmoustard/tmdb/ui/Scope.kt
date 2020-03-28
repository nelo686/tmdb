package es.mrmoustard.tmdb.ui

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

interface Scope : CoroutineScope {

    var job: Job
    var ioDispatcher: CoroutineDispatcher
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    fun initScope() {
        job = SupervisorJob()
    }

    fun cancelScope() {
        job.cancel()
    }

    class Impl : Scope {
        override var ioDispatcher = Dispatchers.IO
        override lateinit var job: Job
    }
}
