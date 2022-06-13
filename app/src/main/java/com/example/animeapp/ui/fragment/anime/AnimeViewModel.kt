package com.example.animeapp.ui.fragment.anime

import com.example.core.base.BaseViewModel
import com.example.domain.anime.use_case.GetRandomAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getRandomAnimeUseCase: GetRandomAnimeUseCase
) : BaseViewModel() {

}