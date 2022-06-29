package com.example.data.network.anime.apiservices

import com.example.data.common.utils.Anime
import com.example.data.common.utils.Pagination
import com.example.data.network.anime.dto.AnimeDto
import com.example.data.network.anime.dto.AnimeEpisodeDto
import com.example.data.network.anime.dto.TempDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface AnimeApi {
    @GET(Anime.search)
    suspend fun getSearchAnime(
        @Query(Pagination.page) page: Int,
        @Query(Pagination.limit) limit: Int,
        @Query("q") search: String
    ): Response<AnimeDto>

    @GET(Anime.top)
    suspend fun getRandomAnime(
        @Query(Pagination.page) page: Int,
        @Query(Pagination.limit) limit: Int,
    ): Response<AnimeDto>

    @GET(Anime.info)
    suspend fun getInfoAnime(
        @Path(Anime.id) id: Int
    ): Response<TempDto>

    @GET(Anime.episodes)
    suspend fun getEpisodesAnime(
        @Path(Anime.id) id: Int,
        @Query(Pagination.page) page: Int,
    ):Response<AnimeEpisodeDto>
}