package es.mrmoustard.tmdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import es.mrmoustard.tmdb.R
import es.mrmoustard.tmdb.app
import es.mrmoustard.tmdb.di.home.HomeModule
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject lateinit var viewModel: HomeViewModel

    val component by lazy {
        activity?.app?.component?.plus(HomeModule())
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_toprated, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component?.inject(fragment = this)
        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: HomeUiModel) {
        when (model) {
            is HomeUiModel.Content -> {}
            is HomeUiModel.Navigate -> {}
        }
    }
}
