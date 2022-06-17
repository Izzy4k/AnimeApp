package com.example.data.network.manga.apiservices

import com.example.data.common.utils.Manga
import com.example.data.common.utils.Pagination
import com.example.data.network.manga.dto.MangaDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MangaApi {
    @GET(Manga.search)
    suspend fun getSearchManga(
        @Query(Pagination.page) page: Int,
        @Query(Pagination.limit) limit: Int,
        @Query("q") search: String
    ): Response<MangaDto>

    @GET(Manga.top)
    suspend fun getRandomManga(
        @Query(Pagination.page) page: Int,
        @Query(Pagination.limit) limit: Int,
    ): Response<MangaDto>
}