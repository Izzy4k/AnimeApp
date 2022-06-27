package com.example.domain.manga.use_case

import androidx.paging.PagingData
import com.example.domain.manga.entity.Manga
import com.example.domain.manga.repo.MangaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopMangaUseCase @Inject constructor(
    private val repository: MangaRepository
) {
    suspend fun getTopManga(searchBy: String): Flow<PagingData<Manga>> =
        repository.getTopManga(searchBy)
}