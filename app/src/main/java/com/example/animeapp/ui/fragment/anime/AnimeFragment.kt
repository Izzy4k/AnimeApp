package com.example.animeapp.ui.fragment.anime

import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.animeapp.R
import com.example.animeapp.databinding.FragmentAnimeBinding
import com.example.animeapp.ui.adapter.DefaultLoadAdapter
import com.example.animeapp.ui.fragment.anime.adapter.AnimeAdapter
import com.example.animeapp.ui.adapter.SliderAdapter
import com.example.animeapp.ui.adapter.TryAgainAction
import com.example.animeapp.ultils.Arguments
import com.example.core.base.BaseFragment
import com.example.domain.anime.entity.Anime
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class AnimeFragment : BaseFragment<FragmentAnimeBinding>(
    FragmentAnimeBinding::inflate
), AnimeAdapter.Result {


    private val viewModel: AnimeViewModel by viewModels()

    private val adapter: AnimeAdapter by lazy { AnimeAdapter(this) }

    override fun setupUI() {
        initAdapters()
        getRandom()
    }

    private fun initAdapters() {
        val tryAgainAction: TryAgainAction = { adapter.retry() }

        val loadAdapter = DefaultLoadAdapter(tryAgainAction)

        val adapterWithLoad = adapter.withLoadStateFooter(loadAdapter)

        requireBinding().rvAnime.adapter = adapterWithLoad
    }

    private fun initPager(list: List<Anime>) {
        val sliderAdapter = SliderAdapter(list)
        requireBinding().sliderAnime.setSliderAdapter(sliderAdapter)
    }

    override fun setupObservers() {
        super.setupObservers()
        observeSearch()
        observeRandom()
        observeTop()
        observeState()
    }

    private fun observeSearch() {
        requireBinding().editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val value = requireBinding().editSearch.text.toString().trim()
                searchBy(value)
            }

        })
    }

    private fun searchBy(value: String) {
        viewModel.setSearchBy(value)
    }

    private fun observeState() {
        adapter.loadStateFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }.launchIn(lifecycleScope)
    }

    private fun handleState(state: CombinedLoadStates) {
        requireBinding().animeProgress.isVisible = state.refresh is LoadState.Loading
    }

    private fun observeTop() {
        viewModel.animeFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleTop(it) }.launchIn(lifecycleScope)
    }

    private suspend fun handleTop(it: PagingData<Anime>) {
        adapter.submitData(it)
    }

    private fun observeRandom() {
        viewModel.animeRandom.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleRandom(it) }.launchIn(lifecycleScope)
    }

    private fun handleRandom(random: List<Anime>) {
        initPager(random)
    }

    private fun getRandom() {
        viewModel.getRandom()
    }

    override fun onClickListener(id: Int) {
        navigateDetail(id)
    }

    private fun navigateDetail(id: Int) {
        val action = AnimeFragmentDirections.actionAnimeFragmentToDetailFragment(
            Arguments(
                id,
                requireContext().getString(R.string.anime)
            )
        )
        findNavController().navigate(action)
    }


}