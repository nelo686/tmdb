package es.mrmoustard.tmdb.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import es.mrmoustard.tmdb.databinding.ViewMovieBinding
import es.mrmoustard.tmdb.domain.entities.Movie
import kotlin.properties.Delegates

class ItemAdapter(
    private val imageBaseUrl: String,
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdatableAdapter {

    companion object {
        private const val IMAGE_RESOLUTION = "w342"
    }

    var items: List<Movie> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ViewMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(
            item = items[position],
            listener = listener,
            imageBaseUrl = imageBaseUrl
        )
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(private val binding: ViewMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie, listener: (Int) -> Unit, imageBaseUrl: String) = with(itemView) {
            setOnClickListener { listener(item.id) }
            binding.ivPoster.load("$imageBaseUrl$IMAGE_RESOLUTION${item.backdropPath}")
            binding.tvTitle.text = item.title
        }
    }
}
