package com.example.data.network.manga.repo

import androidx.paging.PagingData
import com.example.core.base.BaseResult
import com.example.data.network.manga.apiservices.MangaApi
import com.example.domain.manga.entity.Manga
import com.example.domain.manga.repo.MangaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaApi: MangaApi
) : MangaRepository {
    override suspend fun getRandomManga(): Flow<BaseResult<List<Manga>, String>> {
        TODO("Not yet implemented")
    }

    override fun getTopManga(): Flow<PagingData<Manga>> {
        TODO("Not yet implemented")
    }

}