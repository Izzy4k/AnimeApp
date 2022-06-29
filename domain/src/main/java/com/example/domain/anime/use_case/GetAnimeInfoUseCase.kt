package com.example.domain.anime.use_case

import com.example.domain.anime.entity.Anime
import com.example.domain.anime.repo.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeInfoUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend fun getAnimeInfo(id: Int): Flow<Anime> = repository.getAnimeInfo(id)
}