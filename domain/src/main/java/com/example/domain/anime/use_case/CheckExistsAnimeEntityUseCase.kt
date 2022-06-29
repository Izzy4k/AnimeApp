package com.example.domain.anime.use_case

import com.example.domain.anime.repo.AnimeLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckExistsAnimeEntityUseCase @Inject constructor(
    private val repository: AnimeLocalRepository
) {
    suspend fun checkExistsAnimeEntity(id: Int): Flow<Boolean> =
        repository.checkExistsAnimeEntity(id)
}