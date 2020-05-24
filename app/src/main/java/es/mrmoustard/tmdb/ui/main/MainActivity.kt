package es.mrmoustard.tmdb.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.app
import es.mrmoustard.tmdb.databinding.ActivityMainBinding
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    @Inject
    @Named(value = "imageBaseUrl")
    lateinit var imageBaseUrl: String

    val component by lazy {
        app.component.addMainModule().create(activity = this)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(activity = this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_watchlist,
                R.id.navigation_favourites
            )
        )
        setupActionBarWithNavController(navController = navController, configuration = appBarConfig)
        binding.navigationView.setupWithNavController(navController = navController)
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp()

    fun showLoading(show: Boolean) {
        binding.loader.visibility = if (show) View.VISIBLE else View.GONE
    }
}
