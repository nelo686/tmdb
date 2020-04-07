package es.mrmoustard.tmdb.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.domain.entities.Movie
import es.mrmoustard.tmdb.ui.common.inflate
import kotlinx.android.synthetic.main.view_movie.view.*

class TopRatedAdapter : ListAdapter<Movie, TopRatedAdapter.ViewHolder>(DiffCallback()) {

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w342"
    }

    var items = listOf<Movie>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.view_movie))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position in items.indices) {
            holder.bind(getItem(position))
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Movie) = with(itemView) {
            ivPoster.load("$IMAGE_BASE_URL${item.backdropPath}")
            tvTitle.text = item.title
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
}
