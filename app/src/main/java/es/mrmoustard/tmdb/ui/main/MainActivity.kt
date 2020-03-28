package es.mrmoustard.tmdb.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.app
import es.mrmoustard.tmdb.di.list.ListModule
import es.mrmoustard.tmdb.ui.list.ListViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: ListViewModel

    val component by lazy {
        app.component.plus(module = ListModule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(activity = this)
        viewModel.getTopRated()
    }
}
