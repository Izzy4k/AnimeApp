package com.example.data.network.anime.repo

import com.example.core.base.BaseResult
import com.example.core.base.PendingResult
import com.example.core.base.SuccessResult
import com.example.data.network.anime.apiservices.AnimeApi
import com.example.domain.anime.entity.Anime
import com.example.domain.anime.repo.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeApi
) : AnimeRepository {



    override suspend fun getRandomAnime(): Flow<BaseResult<Anime, String>> = flow {
        val result = animeApi.getRandomAnime()
        emit(PendingResult)
        if (result.isSuccessful) {
            val body = result.body()
//                emit(SuccessResult())
        }
    }

}