package es.mrmoustard.tmdb.ui.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import es.mrmoustard.tmdb.databinding.FragmentWatchlistBinding

class WatchListFragment : Fragment() {

    private lateinit var binding: FragmentWatchlistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchlistBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
