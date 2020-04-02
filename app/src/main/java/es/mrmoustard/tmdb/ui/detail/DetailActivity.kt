package es.mrmoustard.tmdb.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    companion object Builder {
        private const val MOVIE_ID = "MOVIE_ID"
        fun create(context: Context, movieId: Int): Intent =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
