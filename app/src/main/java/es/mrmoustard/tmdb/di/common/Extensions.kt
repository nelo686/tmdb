package es.mrmoustard.tmdb.di.common

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline provideViewModel: () -> T): T {
    val vmFactory = createFactory(provideViewModel)

    return ViewModelProvider(this, vmFactory).get(T::class.java)
}

inline fun <reified T : ViewModel> createFactory(crossinline provideViewModel: () -> T):
        ViewModelProvider.NewInstanceFactory =
    object : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <U : ViewModel?> create(modelClass: Class<U>): U {
            return provideViewModel() as U
        }
    }

inline fun <reified T : ViewModel> AppCompatActivity.getViewModel(crossinline provideViewModel: () -> T): T {
    val vmFactory = createFactory(provideViewModel)

    return ViewModelProvider(this, vmFactory).get(T::class.java)
}