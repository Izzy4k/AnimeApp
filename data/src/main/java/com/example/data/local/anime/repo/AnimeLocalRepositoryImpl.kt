package com.example.data.local.anime.repo

import com.example.data.local.anime.dao.AnimeDao
import com.example.domain.anime.repo.AnimeLocalRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AnimeLocalRepositoryImpl @Inject constructor(
    private val animeDao: AnimeDao
) : AnimeLocalRepository {
    override suspend fun checkDataBase(): Flow<Boolean> = callbackFlow {
        if (animeDao.getList().isNotEmpty()) {
            trySend(false)
        } else {
            trySend(true)
        }
        awaitClose {
            trySend(false)
        }
    }
}