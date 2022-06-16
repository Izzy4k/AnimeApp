package com.example.animeapp.ui.fragment.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentDetailBinding
import com.example.core.base.BaseFragment

class DetailFragment :
    BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val arguments: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels()

    override fun setupUI() {
        initDetail()
    }

    private fun initDetail() {
        when (arguments.arg.type) {
            requireContext().getString(R.string.anime) -> {
                initAnime()
                initAnimeTxt()
            }
            requireContext().getString(R.string.manga) -> {
                initManga()
                initMangaTxt()
            }
        }
    }

    private fun initManga() {
        viewModel.getManga(arguments.arg.id)
    }

    private fun initMangaTxt() {
        requireBinding().txtEpisode.text = getString(R.string.episode_chapter)
    }

    private fun initAnimeTxt() {
        requireBinding().txtEpisode.text = requireContext().getString(R.string.episode_list)
    }

    private fun initAnime() {
        viewModel.getAnime(arguments.arg.id)
    }
}
