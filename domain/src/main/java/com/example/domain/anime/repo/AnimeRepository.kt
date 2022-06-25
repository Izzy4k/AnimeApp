package com.example.domain.anime.repo

import androidx.paging.PagingData
import com.example.core.base.BaseResult
import com.example.domain.anime.entity.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getRandomAnime(): Flow<BaseResult<List<Anime>, String>>

    suspend fun getSearchAnime(searchBy: String): Flow<PagingData<Anime>>

    suspend fun getAnimeByID(id: Int):  Flow<BaseResult<Anime, String>>
}