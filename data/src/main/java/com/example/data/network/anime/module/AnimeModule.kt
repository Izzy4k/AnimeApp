package com.example.data.network.anime.module

import com.example.data.network.anime.apiservices.AnimeApi
import com.example.data.network.anime.repo.AnimeRepositoryImpl
import com.example.domain.anime.repo.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AnimeModule {

    @Provides
    fun provideAnimeRepo(animeApi: AnimeApi): AnimeRepository = AnimeRepositoryImpl(animeApi)

    @Provides
    @Singleton
    fun provideAnimeApi(retrofit: Retrofit): AnimeApi = retrofit.create(AnimeApi::class.java)
}