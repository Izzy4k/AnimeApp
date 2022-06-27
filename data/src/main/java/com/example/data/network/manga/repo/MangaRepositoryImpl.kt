package com.example.data.network.manga.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.base.BaseResult
import com.example.core.base.ErrorResult
import com.example.core.base.SuccessResult
import com.example.core.base.fromToList
import com.example.data.network.manga.apiservices.MangaApi
import com.example.data.network.manga.source.MangaPageLoader
import com.example.data.network.manga.source.MangaPagingSource
import com.example.data.network.manga.utils.mappers.MangaDtoToData
import com.example.data.network.manga.utils.mappers.MangaDtoToPaging
import com.example.domain.manga.entity.Manga
import com.example.domain.manga.entity.MangaPaging
import com.example.domain.manga.repo.MangaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MangaRepositoryImpl @Inject constructor(
    private val mangaApi: MangaApi
) : MangaRepository {

    private val mangaDtoToData = MangaDtoToData()

    private val mangaDtoToPaging = MangaDtoToPaging()

    private val random = (0..1000).random()

    override suspend fun getRandomManga(): Flow<BaseResult<List<Manga>, String>> = flow {
        val result = mangaApi.getRandomManga(random, PAGE_RANDOM)
        if (result.isSuccessful) {
            val body = result.body()
            body?.body?.let {
                val temp = mangaDtoToData.fromToList(it)
                if (temp != null) {
                    emit(SuccessResult(temp))
                }
            }
        } else {
            emit(ErrorResult(result.message()))
        }
    }


    override suspend fun getTopManga(searchBy: String): Flow<PagingData<Manga>> {
        val loader: MangaPageLoader = { page, limit ->
            getManga(page, limit, searchBy)
        }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { MangaPagingSource(loader, PAGE_SIZE) }
        ).flow
    }

    private suspend fun getManga(page: Int, limit: Int, searchBy: String): MangaPaging =
        withContext(Dispatchers.IO) {
            val result = mangaApi.getSearchManga(page, limit, searchBy)
            return@withContext mangaDtoToPaging.invoke(result.body()!!)
        }


    private companion object {
        const val PAGE_SIZE = 10
        const val PAGE_RANDOM = 3
    }

}