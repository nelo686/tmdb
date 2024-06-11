package es.mrmoustard.tmdb.ui.detail

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.text.toSpanned
import androidx.lifecycle.Observer
import coil.load
import dagger.Lazy
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.app
import es.mrmoustard.tmdb.data.datasource.database.entities.MovieStatus
import es.mrmoustard.tmdb.databinding.ActivityMovieDetailsBinding
import es.mrmoustard.tmdb.domain.entities.MovieDetail
import es.mrmoustard.tmdb.ui.common.ErrorSnackbarStyle
import es.mrmoustard.tmdb.ui.common.showMessage
import es.mrmoustard.tmdb.ui.common.spanWith
import es.mrmoustard.tmdb.ui.common.tintColour
import es.mrmoustard.tmdb.ui.detail.DetailUiModel.*
import javax.inject.Inject
import javax.inject.Named

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelInjection: Lazy<DetailViewModel>
    @Inject
    @Named(value = "imageBaseUrl")
    lateinit var imageBaseUrl: String

    private val viewModel: DetailViewModel by lazy {
        viewModelInjection.get()
    }

    companion object Builder {
        const val MOVIE_ID = "MOVIE_ID"
        private const val BOLD_TEXT_SIZE = 21
        private const val IMAGE_RESOLUTION = "w780"
    }

    private val component by lazy {
        app.component.addDetailModule().create(activity = this)
    }

    private val binding: ActivityMovieDetailsBinding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(activity = this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.onViewCreated(intent.extras?.getInt(MOVIE_ID) ?: -1)
        viewModel.model.observe(this, Observer(::updateUi))
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.ivFavourite.setOnClickListener { viewModel.onFavouriteClicked() }
        binding.ivWannaWatch.setOnClickListener { viewModel.onWannaWatchItClicked() }
    }

    private fun updateUi(model: DetailUiModel) {
        binding.loader.visibility = if (model is Loading) View.VISIBLE else View.GONE

        when (model) {
            is Content -> setContent(model.movie)
            is ErrorResponse -> showMessage(
                view = binding.clRoot,
                style = ErrorSnackbarStyle(message = getString(R.string.something_happen))
            )
            is Flags -> setButtonsStatus(flags = model.flags)
            else -> {}
        }
    }

    private fun setContent(movie: MovieDetail) {
        binding.ivPoster.load("$imageBaseUrl$IMAGE_RESOLUTION${movie.backdropPath}")
        binding.tvTitle.text = movie.title

        setTagline(tagline = movie.tagline)

        with(binding.highlight) {
            tvAverage.text = SpannableString("${movie.voteAverage}/10").apply {
                spanWith(movie.voteAverage.toString()) {
                    what = listOf(AbsoluteSizeSpan(BOLD_TEXT_SIZE, true), StyleSpan(Typeface.BOLD))
                    flags = Spanned.SPAN_EXCLUSIVE_INCLUSIVE
                }
            }.toSpanned()

            tvVoteCount.text = "${movie.voteCount} votes"
            tvReleaseDate.text = "${tvReleaseDate.text}: ${movie.releaseDate}"
            tvGenres.text = "${tvGenres.text}: ${movie.genres.map { it.name }.readList()}"
            tvOriginalTitle.text = "${tvOriginalTitle.text}: ${movie.originalTitle}"
            tvOriginalLanguage.text = "${tvOriginalLanguage.text}: ${movie.originalLanguage}"
            tvSpokenLanguages.text =
                "${tvSpokenLanguages.text}: ${movie.spokenLanguages.map { it.name }.readList()}"
        }

        binding.overviewHeader.tvHeaderTitle.text = getString(R.string.overview_header)
        binding.tvOverview.text = movie.overview
        setButtonsStatus(
            flags = MovieStatus(
                id = movie.id,
                favourite = movie.favourite,
                wannaWatchIt = movie.wannaWatchIt
            )
        )
    }

    private fun setTagline(tagline: String) {
        if (tagline.isNotEmpty()) {
            binding.tvTagLine.text = "\"${tagline}\""
        } else {
            ConstraintSet().apply {
                clone(binding.clRoot)
                connect(
                    R.id.highlight, ConstraintSet.TOP,
                    R.id.tvTitle, ConstraintSet.BOTTOM
                )
                applyTo(binding.clRoot)
            }
            binding.tvTagLine.visibility = View.GONE
        }
    }

    private fun List<String>.readList(): String =
        when (size) {
            0 -> getString(R.string.not_available)
            1 -> first()
            else -> {
                var result: String = first()
                for (i in 1 until size) {
                    result += ", ${get(i)}"
                }
                result
            }
        }

    private fun setButtonsStatus(flags: MovieStatus) {
        val favColour = when (flags.favourite) {
            true -> R.color.yellow
            false -> android.R.color.white
        }
        binding.ivFavourite.tintColour(colour = favColour)

        val watchColour = when (flags.wannaWatchIt) {
            true -> R.color.colorAccent
            false -> android.R.color.white
        }
        binding.ivWannaWatch.tintColour(colour = watchColour)
    }
}
