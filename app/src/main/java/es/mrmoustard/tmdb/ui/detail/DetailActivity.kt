package es.mrmoustard.tmdb.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.app
import es.mrmoustard.tmdb.di.detail.DetailModule
import es.mrmoustard.tmdb.ui.detail.DetailUiModel.Loading
import kotlinx.android.synthetic.main.view_progress.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: DetailViewModel

    companion object Builder {
        private const val MOVIE_ID = "MOVIE_ID"

        fun create(context: Context, movieId: Int): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
    }

    private val component by lazy {
        app.component.plus(module = DetailModule())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(activity = this)

        viewModel.onViewCreated(intent.extras?.getInt(MOVIE_ID) ?: -1)
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: DetailUiModel) {
        loader.visibility = if (model is Loading) View.VISIBLE else View.GONE
    }
}
