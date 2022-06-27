package com.example.domain.anime.repo

import kotlinx.coroutines.flow.Flow

interface AnimeLocalRepository {
   suspend fun checkDataBase(): Flow<Boolean>
}