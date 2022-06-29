package com.example.data.local.manga.repo

import com.example.data.local.manga.dao.MangaDao
import com.example.domain.manga.entity.Manga
import com.example.domain.manga.repo.MangaLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MangaLocalRepositoryImpl @Inject constructor(
    private val mangaDao: MangaDao
) : MangaLocalRepository {
    override suspend fun checkDataBase(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun checkExistsMangaEntity(id: Int): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun actionMangaEntity(anime: Manga, isExists: Boolean) {
        TODO("Not yet implemented")
    }


    override suspend fun getMangaEntity(): Flow<List<Manga>> {
        TODO("Not yet implemented")
    }
}