package com.example.domain.anime.use_case

import com.example.core.base.BaseResult
import com.example.domain.anime.entity.Anime
import com.example.domain.anime.repo.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomAnimeUseCase
@Inject
constructor(
    private val repository: AnimeRepository
) {
    suspend fun getRandomAnime(): Flow<BaseResult<List<Anime>, String>> =
        repository.getRandomAnime()
}