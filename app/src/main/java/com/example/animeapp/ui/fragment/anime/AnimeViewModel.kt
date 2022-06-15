package com.example.animeapp.ui.fragment.anime

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.core.base.ErrorResult
import com.example.core.base.PendingResult
import com.example.core.base.SuccessResult
import com.example.domain.anime.entity.Anime
import com.example.domain.anime.use_case.GetRandomAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getRandomAnimeUseCase: GetRandomAnimeUseCase
) : BaseViewModel() {

    private val _animeRandom = MutableStateFlow<List<Anime>>(mutableListOf())
    val animeRandom: StateFlow<List<Anime>> get() = _animeRandom

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
}