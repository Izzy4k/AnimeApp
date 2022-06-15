package com.example.animeapp.ui.fragment.anime

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.animeapp.databinding.FragmentAnimeBinding
import com.example.core.base.BaseFragment
import com.example.domain.anime.entity.Anime
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AnimeFragment : BaseFragment<FragmentAnimeBinding>(
    FragmentAnimeBinding::inflate
) {

    private val isStart: Boolean = true

    private val viewModel: AnimeViewModel by viewModels()

    override fun setupUI() {
        initPager()
        getRandom()
    }

    private fun initPager() {
    }

    override fun setupObservers() {
        super.setupObservers()
        observeRandom()
    }

    private fun observeRandom() {
        viewModel.animeRandom.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleRandom(it) }.launchIn(lifecycleScope)
    }

    private fun handleRandom(random: List<Anime>) {
        Log.e("ABOBA", "RANDOM ")
    }

    private fun getRandom() {
        viewModel.getRandom()
    }


}