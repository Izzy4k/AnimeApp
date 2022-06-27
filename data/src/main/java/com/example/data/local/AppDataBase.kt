package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.anime.dao.AnimeDao
import com.example.data.local.anime.entities.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getAnimeDao(): AnimeDao
}