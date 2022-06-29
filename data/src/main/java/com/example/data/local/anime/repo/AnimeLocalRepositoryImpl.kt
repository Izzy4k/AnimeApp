package com.example.data.local.anime.repo

import com.example.core.base.fromToList
import com.example.data.local.anime.dao.AnimeDao
import com.example.data.local.anime.utils.mappers.AnimeEntityToAnime
import com.example.data.local.anime.utils.mappers.AnimeToAnimeEntity
import com.example.domain.anime.entity.Anime
import com.example.domain.anime.repo.AnimeLocalRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AnimeLocalRepositoryImpl @Inject constructor(
    private val animeDao: AnimeDao
) : AnimeLocalRepository {


    private val animeEntityToAnime = AnimeEntityToAnime()

    private val animeToAnimeEntity = AnimeToAnimeEntity()

    override suspend fun checkAnimeData(): Flow<Boolean> = callbackFlow {
        animeDao.getCheckData().collect {
            if (it.isEmpty()) {
                trySend(true)
            } else {
                trySend(false)
            }
        }
        awaitClose {
            trySend(false)
        }
    }

    override suspend fun checkExistsAnimeEntity(id: Int): Flow<Boolean> = callbackFlow {
        animeDao.getCheckData().collect {
            for (item in it) {
                if (item.mal_id == id) {
                    trySend(true)
                } else {
                    trySend(false)
                }
            }
        }
        awaitClose {
            trySend(false)
        }
    }

    override suspend fun actionAnimeEntity(anime:Anime,id: Int, isExists: Boolean) {
        if (isExists) {
            val animeEntity = animeDao.getAnimeEntityById(id)
            animeDao.deleteAnimeEntity(animeEntity)
        } else {
            val animeEntity = animeToAnimeEntity.invoke(anime)
            animeDao.createAnimeEntity(animeEntity)
        }
    }


    override suspend fun getAnimeEntity(): Flow<List<Anime>> = flow {
        animeDao.getCheckData().collect {
            val animeList = animeEntityToAnime.fromToList(it)
            if (animeList != null) {
                emit(animeList)
            }
        }
    }


}