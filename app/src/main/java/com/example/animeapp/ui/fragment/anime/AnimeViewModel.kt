package com.example.animeapp.ui.fragment.anime

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.base.BaseViewModel
import com.example.core.base.ErrorResult
import com.example.core.base.PendingResult
import com.example.core.base.SuccessResult
import com.example.domain.anime.entity.Anime
import com.example.domain.anime.use_case.GetRandomAnimeUseCase
import com.example.domain.anime.use_case.GetSearchAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getRandomAnimeUseCase: GetRandomAnimeUseCase,
    private val getSearchAnimeUseCase: GetSearchAnimeUseCase
) : BaseViewModel() {

    private val _animeRandom = MutableStateFlow<List<Anime>>(mutableListOf())
    val animeRandom: StateFlow<List<Anime>> get() = _animeRandom


    val animeFlow: Flow<PagingData<Anime>>

    private val searchBy = MutableLiveData("")

    /*
    * Поищу реализацию лучше  (криво работает) Не трогать!!
    * */
    init {
        animeFlow = searchBy.asFlow()
            .debounce(500)
            .flatMapLatest {
                getSearchAnimeUseCase.getSearchAnime(it)
            }
            .cachedIn(viewModelScope)
    }


    fun getRandom() {
        viewModelScope.launch(Dispatchers.IO) {
            getRandomAnimeUseCase.getRandomAnime()
                .onStart {
                    setLoading()
                }.catch {
                    hideLoading()
                }.collect {
                    when (it) {
                        is ErrorResult -> hideLoading()
                        PendingResult -> setLoading()
                        is SuccessResult -> {
                            hideLoading()
                            _animeRandom.value = it.data
                        }
                    }
                }
        }
    }

    fun setSearchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }

}