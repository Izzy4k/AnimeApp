package com.example.data.network.anime.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.base.BaseResult
import com.example.core.base.ErrorResult
import com.example.core.base.PendingResult
import com.example.core.base.SuccessResult
import com.example.data.common.utils.mappers.AnimeDtoToData
import com.example.data.network.anime.apiservices.AnimeApi
import com.example.data.network.anime.dto.AnimeDto
import com.example.data.network.anime.source.AnimePageLoader
import com.example.data.network.anime.source.AnimePagingSource
import com.example.domain.anime.entity.Anime
import com.example.domain.anime.repo.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeApi: AnimeApi
) : AnimeRepository {

    private val list = mutableListOf<Anime>()

    private val animeDtoToData = AnimeDtoToData()

    override suspend fun getRandomAnime(): Flow<BaseResult<List<Anime>, String>> {
        return flow {
            val result = animeApi.getRandomAnime()
            emit(PendingResult)
            if (list.size >= 4) {
                list.removeAt(0)
            }
            if (result.isSuccessful) {
                val body = result.body()
                body?.let { list.add(animeDtoToData.invoke(it)) }
                emit(SuccessResult(list))
            } else {
                emit(ErrorResult(result.message()))
            }
        }
    }

    override fun getTopAnime(): Flow<PagingData<Anime>> {
        val loader: AnimePageLoader = { page ->
            getAnime(page)
        }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { AnimePagingSource(loader, PAGE_SIZE) }
        ).flow
    }

    private suspend fun getAnime(page: Int): AnimeDto = withContext(Dispatchers.IO) {
        val result = animeApi.getTopAnime(page)
        return@withContext result.body()!!
    }

    private companion object {
        const val PAGE_SIZE = 20
    }
}