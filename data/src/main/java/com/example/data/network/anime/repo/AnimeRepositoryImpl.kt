package com.example.data.network.anime.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.base.*
import com.example.data.network.anime.utils.mappers.AnimeDtoToPaging
import com.example.data.network.anime.apiservices.AnimeApi
import com.example.data.network.anime.source.AnimePageLoader
import com.example.data.network.anime.source.AnimePagingSource
import com.example.data.network.anime.utils.mappers.AnimeDtoToData
import com.example.domain.anime.entity.Anime
import com.example.domain.anime.entity.AnimePaging
import com.example.domain.anime.repo.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeApi
) : AnimeRepository {


    private val animeDtoToPaging = AnimeDtoToPaging()

    private val animeDtoToData = AnimeDtoToData()

    private val random = (100..1000).random()

    override suspend fun getRandomAnime(): Flow<BaseResult<List<Anime>, String>> = flow {
        val result = animeApi.getRandomAnime(random, PAGE_RANDOM)
        emit(PendingResult)
        if (result.isSuccessful) {
            val body = result.body()
            body?.body?.let {
                val temp = animeDtoToData.fromToList(it)
                if (temp != null) {
                    emit(SuccessResult(temp))
                }
            }
        } else {
            emit(ErrorResult(result.message()))
        }
    }

    override suspend fun getSearchAnime(searchBy: String): Flow<PagingData<Anime>> {
        val loader: AnimePageLoader = { page, limit ->
            getAnime(page, limit, searchBy)
        }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { AnimePagingSource(loader, PAGE_SIZE) }
        ).flow
    }

    private suspend fun getAnime(page: Int, limit: Int, searchBy: String): AnimePaging =
        withContext(Dispatchers.IO) {
            val result = animeApi.getSearchAnime(page, limit, searchBy)
            return@withContext animeDtoToPaging.invoke(result.body()!!)
        }

    private companion object {
        const val PAGE_SIZE = 10
        const val PAGE_RANDOM = 3
    }
}