package com.example.domain.anime.use_case

import com.example.domain.anime.repo.AnimeLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckAnimeDataUseCase @Inject constructor(
    private val repository: AnimeLocalRepository
) {
    suspend fun checkAnimeData():Flow<Boolean> = repository.checkAnimeData()
}