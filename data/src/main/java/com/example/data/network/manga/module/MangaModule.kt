package com.example.data.network.manga.module

import com.example.data.network.manga.apiservices.MangaApi
import com.example.data.network.manga.repo.MangaRepositoryImpl
import com.example.domain.manga.repo.MangaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MangaModule {

    @Provides
    @Singleton
    fun provideMangaApi(retrofit: Retrofit): MangaApi = retrofit.create(MangaApi::class.java)

    @Provides
    fun provideMangaRepository(mangaApi: MangaApi): MangaRepository = MangaRepositoryImpl(mangaApi)
}