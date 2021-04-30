package com.amrmustafa.casestudy.activities

import android.os.Bundle
import android.view.Menu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.amrmustafa.casestudy.R
import com.amrmustafa.casestudy.adapters.FavoritesAdapter
import com.amrmustafa.casestudy.models.FavoritePresentation
import com.amrmustafa.casestudy.models.states.FavoritesViewState
import com.amrmustafa.casestudy.databinding.ActivityFavoritesBinding
import com.amrmustafa.casestudy.utils.*
import com.amrmustafa.casestudy.utils.Constants
import com.amrmustafa.casestudy.utils.startActivity
import com.amrmustafa.casestudy.viewmodel.FavoritesViewModel
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class FavoritesActivity : BaseActivity() {

    private val favoritesViewModel by viewModel<FavoritesViewModel>()

    private lateinit var binding: ActivityFavoritesBinding

    private val favoritesAdapter = FavoritesAdapter {
        startActivity<FavoriteDetailsActivity> {
            putExtra(Constants.FAVORITE_KEY, it)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorites)
        observeFavoritesViewState()
    }

    override fun onResume() {
        super.onResume()
        favoritesViewModel.getAllFavorites()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun observeFavoritesViewState() {
        favoritesViewModel.favoritesViewState.observe(this, Observer { state ->

            handleFavoritesLoading(state)

            state.favorites?.let { favorites ->
                if (favorites.isNotEmpty()) {
                    handleFavorites(favorites)
                } else {
                    binding.noFavoritesTextView.show()
                }
            }

            handleFavoritesError(state)
        })
    }


    private fun handleFavoritesLoading(state: FavoritesViewState) {
        if (state.isLoading) {
            binding.favoritesProgressBar.show()
        } else {
            binding.favoritesProgressBar.hide()
        }
    }


    private fun handleFavorites(favorites: List<FavoritePresentation>) {
        binding.noFavoritesTextView.hide()
        binding.favoritesRecyclerView.show()
        binding.favoritesRecyclerView.apply {
            adapter = ScaleInAnimationAdapter(favoritesAdapter.apply {
                submitList(favorites)
            })
        }
    }

    private fun handleFavoritesError(state: FavoritesViewState) {
        state.error?.run {
            binding.noFavoritesTextView.show()
            showSnackbar(
                binding.favoritesRecyclerView,
                getString(this.message),
                isError = true
            )
        }
    }


}
