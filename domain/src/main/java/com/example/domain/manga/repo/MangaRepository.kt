package com.example.domain.manga.repo

import androidx.paging.PagingData
import com.example.core.base.BaseResult
import com.example.domain.manga.entity.Manga
import kotlinx.coroutines.flow.Flow

interface MangaRepository {
    suspend fun getRandomManga(): Flow<BaseResult<List<Manga>, String>>

    fun getTopManga(): Flow<PagingData<Manga>>
}