package com.example.data.local.anime.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.local.anime.entities.AnimeEntity

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime_entity")
    suspend fun getList(): List<AnimeEntity>
}