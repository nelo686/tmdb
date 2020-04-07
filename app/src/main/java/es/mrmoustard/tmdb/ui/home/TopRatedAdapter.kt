package es.mrmoustard.tmdb.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.domain.entities.Movie
import es.mrmoustard.tmdb.ui.common.AutoUpdatableAdapter
import es.mrmoustard.tmdb.ui.common.inflate
import kotlinx.android.synthetic.main.view_movie.view.*
import kotlin.properties.Delegates

class TopRatedAdapter(private val listener: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), AutoUpdatableAdapter {

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w342"
    }

    var items: List<Movie> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o.id == n.id }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.view_movie))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(item = items[position], listener = listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Movie, listener: (Int) -> Unit) = with(itemView) {
            setOnClickListener { listener(item.id) }
            ivPoster.load("$IMAGE_BASE_URL${item.backdropPath}")
            tvTitle.text = item.title
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
}
