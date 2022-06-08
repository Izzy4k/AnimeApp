package com.example.domain.anime.repo

import com.example.core.base.BaseResult
import com.example.domain.anime.entity.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getRandomAnime(): Flow<BaseResult<Anime, String>>
}