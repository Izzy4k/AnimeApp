package com.example.data.local.anime.module

//import com.example.data.local.AppDataBase
import com.example.data.local.anime.dao.AnimeDao
//import com.example.data.local.anime.repo.AnimeLocalRepositoryImpl
import com.example.domain.anime.repo.AnimeLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//class AnimeLocalModule {
//
//    @Provides
//    @Singleton
//    fun provideAnimeDao(appDataBase: AppDataBase): AnimeDao = appDataBase.getAnimeDao()
//
//    @Provides
//    fun provideAnimeLocalRepo(animeDao: AnimeDao): AnimeLocalRepository =
//        AnimeLocalRepositoryImpl(animeDao)
//}