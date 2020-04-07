package es.mrmoustard.tmdb.ui.common

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import es.mrmoustard.tmdb.domain.entities.Movie

//abstract class BaseAdapter<T>(
//    private val listener: (Int) -> Unit
//) : ListAdapter<T, ViewHolder>(DiffCallback()) {
//
//    var items = listOf<T>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
//        getViewHolder(parent = parent, viewType = viewType)
//
//    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        if (position in items.indices) {
//            (holder as Binder<T>).bind(item = items[position], listener = listener)
//        }
//    }
//
//    interface Binder<in T> {
//        fun bind(item: T, listener: (Int) -> Unit)
//    }
//}
//
//class DiffCallback<T> : DiffUtil.ItemCallback<T>() {
//    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.id == newItem.id
//
//    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
//}
