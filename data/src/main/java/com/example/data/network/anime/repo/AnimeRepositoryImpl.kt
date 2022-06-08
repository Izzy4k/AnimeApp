package com.example.data.network.anime.repo

import com.example.data.network.anime.apiservices.AnimeApi
import com.example.domain.anime.repo.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeApi
) : AnimeRepository {

}