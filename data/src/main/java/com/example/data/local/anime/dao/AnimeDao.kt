package com.example.data.local.anime.dao

import androidx.room.*
import com.example.data.local.anime.entities.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime_entity")
    fun getCheckData(): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAnimeEntity(animeEntity: AnimeEntity)

    @Delete
    suspend fun deleteAnimeEntity(animeEntity: AnimeEntity)
}