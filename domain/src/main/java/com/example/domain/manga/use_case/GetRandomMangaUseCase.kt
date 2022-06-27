package com.example.domain.manga.use_case

import com.example.core.base.BaseResult
import com.example.domain.manga.entity.Manga
import com.example.domain.manga.repo.MangaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRandomMangaUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    suspend fun getRandomManga(): Flow<BaseResult<List<Manga>, String>> =
        repository.getRandomManga()
}