package es.mrmoustard.tmdb.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.mrmoustard.tmdb.R

class WatchListFragment : Fragment() {

    companion object {
        fun newInstance(): WatchListFragment = WatchListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_watchlist, container, false)
}
