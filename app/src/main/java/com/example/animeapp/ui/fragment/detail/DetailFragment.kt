package com.example.animeapp.ui.fragment.detail

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentDetailBinding
import com.example.core.base.BaseFragment
import com.example.domain.anime.entity.Anime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
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
                initAnimeTxt()
                initBtn()
                initAnimeExists()
            }
            requireContext().getString(R.string.manga) -> {
                initMangaTxt()
            }
        }
    }

    private fun initBtn() {
        requireBinding().imgFavorite.setOnClickListener {
            viewModel.actionAnime(arguments.arg.id)
        }
    }

    private fun initAnimeExists() {
        viewModel.checkExistsForAnime(arguments.arg.id)
    }

    override fun setupObservers() {
        super.setupObservers()
        observeDetail()
    }

    private fun observeDetail() {
        when (arguments.arg.type) {
            requireContext().getString(R.string.anime) -> {
                observeAnimeInfo()
                observeAnimeExists()
            }
            requireContext().getString(R.string.manga) -> {
            }
        }
    }

    private fun observeAnimeExists() {
        viewModel.checkExistsAnime.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleAnimeExists(it) }.launchIn(lifecycleScope)
    }

    private fun handleAnimeExists(exists: Boolean) {
        if (exists) {
            setRedTxtFavorite()
            setAnimeExists()
        } else {
            setGrayTxtFavorite()
            setAnimeNotExists()
        }
    }

    private fun setAnimeNotExists() {
        requireBinding().imgFavorite.load(R.drawable.ic_union)
        requireBinding().txtFavorite.text = getString(R.string.in_favorite)
    }

    private fun setGrayTxtFavorite() {
        requireBinding().txtFavorite.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.gray
            )
        )
    }

    private fun setAnimeExists() {
        requireBinding().imgFavorite.load(R.drawable.ic_union_light)
        requireBinding().txtFavorite.text = getString(R.string.added)
    }

    private fun setRedTxtFavorite() {
        requireBinding().txtFavorite.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.red
            )
        )
    }

    private fun observeAnimeInfo() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getAnimeInfo(arguments.arg.id)
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .onEach { handleAnimeInfo(it) }.launchIn(this)
        }
    }

    private fun handleAnimeInfo(it: Anime) {
        requireBinding().imageAnime.load(it.image)
        requireBinding().tvDetailTitle.text = it.title
        requireBinding().txtDescription.text = it.genre
    }



    private fun initMangaTxt() {
        requireBinding().txtEpisode.text = getString(R.string.episode_chapter)
    }

    private fun initAnimeTxt() {
        requireBinding().txtEpisode.text = requireContext().getString(R.string.episode_list)
    }


}
