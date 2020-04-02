package es.mrmoustard.tmdb.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.domain.entities.Movie
import es.mrmoustard.tmdb.ui.common.BaseAdapter
import es.mrmoustard.tmdb.ui.common.inflate
import kotlinx.android.synthetic.main.view_movie.view.*

class TopRatedAdapter(listener: (Int) -> Unit) : BaseAdapter<Movie>(listener = listener) {

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/w342"
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(parent.inflate(R.layout.view_movie))

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), Binder<Movie> {
        override fun bind(item: Movie, listener: (Int) -> Unit) = with(itemView) {
            setOnClickListener { listener(item.id) }
            ivPoster.load("$BASE_URL${item.backdropPath}")
            tvTitle.text = item.title
        }
    }
}
