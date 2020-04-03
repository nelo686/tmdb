package es.mrmoustard.tmdb.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.ui.favourites.FavouritesFragment
import es.mrmoustard.tmdb.ui.home.HomeFragment
import es.mrmoustard.tmdb.ui.watchlist.WatchListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_progress.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView.setOnNavigationItemSelectedListener(navListener)
        openFragment(HomeFragment.newInstance())
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportActionBar?.title = "Home"
                val homeFragment = HomeFragment.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_watchlist -> {
                supportActionBar?.title = "Watchlist"
                val wlFragment = WatchListFragment.newInstance()
                openFragment(wlFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favourites -> {
                supportActionBar?.title = "Favorites"
                val favFragment = FavouritesFragment.newInstance()
                openFragment(favFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun showLoading(show: Boolean) {
        loader.visibility = if (show) View.VISIBLE else View.GONE
    }
}
