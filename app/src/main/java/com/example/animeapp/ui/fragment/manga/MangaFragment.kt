package com.example.animeapp.ui.fragment.manga

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
import com.example.animeapp.databinding.FragmentMangaBinding
import com.example.animeapp.ui.adapter.DefaultLoadAdapter
import com.example.animeapp.ui.adapter.TryAgainAction
import com.example.animeapp.ui.fragment.anime.AnimeFragmentDirections
import com.example.animeapp.ui.fragment.manga.adapter.MangaAdapter
import com.example.animeapp.ui.fragment.manga.adapter.SliderMangaAdapter
import com.example.animeapp.ultils.Arguments
import com.example.core.base.BaseFragment
import com.example.domain.manga.entity.Manga
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
@FlowPreview
@AndroidEntryPoint
class MangaFragment : BaseFragment<FragmentMangaBinding>(
    FragmentMangaBinding::inflate
), MangaAdapter.Result {

    private val viewModel: MangaViewModel by viewModels()

    private val adapter: MangaAdapter by lazy {
        MangaAdapter(this)
    }

    override fun setupUI() {
        initAdapters()
        getRandom()
    }

    private fun getRandom() {
        viewModel.getRandom()
    }

    private fun initAdapters() {
        val tryAgainAction: TryAgainAction = { adapter.retry() }

        val loadAdapter = DefaultLoadAdapter(tryAgainAction)

        val adapterWithLoad = adapter.withLoadStateFooter(loadAdapter)

        requireBinding().rvManga.adapter = adapterWithLoad
    }

    override fun setupObservers() {
        super.setupObservers()
        observeSearch()
        observeRandom()
        observeTop()
        observeState()
    }

    private fun observeState() {
        adapter.loadStateFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleState(it) }.launchIn(lifecycleScope)
    }

    private fun handleState(state: CombinedLoadStates) {
        requireBinding().mangaProgress.isVisible = state.refresh is LoadState.Loading
    }

    private fun observeTop() {
        viewModel.mangaFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleTop(it) }.launchIn(lifecycleScope)
    }

    private suspend fun handleTop(it: PagingData<Manga>) {
        adapter.submitData(it)
    }

    private fun observeRandom() {
        viewModel.mangaRandom.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { handleRandom(it) }.launchIn(lifecycleScope)
    }

    private fun handleRandom(random: List<Manga>) {
        initPager(random)
    }

    private fun initPager(random: List<Manga>) {
        val sliderAdapter = SliderMangaAdapter(random)
        requireBinding().sliderManga.setSliderAdapter(sliderAdapter)
    }

    private fun observeSearch() {
        requireBinding().editSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                val value = requireBinding().editSearch.text.toString()
                searchBy(value)
            }

        })
    }

    private fun searchBy(value: String) {
        viewModel.setSearchBy(value)
    }

    override fun onClickListener(id: Int) {
        navigateDetail(id)
    }

    private fun navigateDetail(id: Int) {
        val action = MangaFragmentDirections.actionMangaFragmentToDetailFragment(
            Arguments(
                id,
                requireContext().getString(R.string.manga)
            )
        )
        findNavController().navigate(action)
    }
}