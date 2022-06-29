package com.example.domain.manga.repo

import com.example.domain.manga.entity.Manga
import kotlinx.coroutines.flow.Flow

interface MangaLocalRepository {
    suspend fun checkDataBase(): Flow<Boolean>
    suspend fun checkExistsMangaEntity(id: Int): Flow<Boolean>
    suspend fun actionMangaEntity(anime: Manga, isExists: Boolean)
    suspend fun getMangaEntity(): Flow<List<Manga>>
}