package com.example.domain.anime.repo

import com.example.domain.anime.entity.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeLocalRepository {
    suspend fun checkAnimeData(): Flow<Boolean>
    suspend fun checkExistsAnimeEntity(id: Int): Flow<Boolean>
    suspend fun actionAnimeEntity(anime: Anime, isExists: Boolean)
    suspend fun getAnimeEntity(): Flow<List<Anime>>
}