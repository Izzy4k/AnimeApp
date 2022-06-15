package com.example.animeapp.ui.fragment.detail_anime

import com.example.core.base.BaseViewModel
import com.example.data.network.anime.repo.AnimeRepositoryImpl
import com.example.domain.anime.useCase.GetAnimeBySearchUseCase
import javax.inject.Inject

class DetailAnimeViewModel @Inject constructor(repositoryImpl: AnimeRepositoryImpl) :
    BaseViewModel() {

        private val getSearchAnime = GetAnimeBySearchUseCase(repositoryImpl)


}