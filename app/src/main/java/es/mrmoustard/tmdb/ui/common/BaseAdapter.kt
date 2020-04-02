package es.mrmoustard.tmdb.ui.common

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseAdapter<T>(
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    var items = listOf<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        getViewHolder(parent = parent, viewType = viewType)

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position in items.indices) {
            (holder as Binder<T>).bind(item = items[position], listener = listener)
        }
    }

    interface Binder<in T> {
        fun bind(item: T, listener: (Int) -> Unit)
    }
}
