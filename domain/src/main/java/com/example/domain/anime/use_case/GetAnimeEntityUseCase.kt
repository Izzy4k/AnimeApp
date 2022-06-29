package com.example.domain.anime.use_case

import com.example.domain.anime.entity.Anime
import com.example.domain.anime.repo.AnimeLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeEntityUseCase @Inject constructor(
    private val repository: AnimeLocalRepository
) {
    suspend fun getAnimeEntity():Flow<List<Anime>> = repository.getAnimeEntity()
}