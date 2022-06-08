package com.example.data.network.anime.apiservices

import com.example.data.common.utils.Anime
import com.example.data.network.anime.dto.AnimeTopDto
import com.example.data.network.anime.dto.Body
import retrofit2.Response
import retrofit2.http.GET


interface AnimeApi {
    @GET(Anime.top)
    suspend fun getTopAnime(): Response<AnimeTopDto>

    @GET(Anime.random)
    suspend fun getRandomAnime (): Response<Body>
}