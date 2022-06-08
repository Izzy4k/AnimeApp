package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.room.dao.AnimeDao

@Database(entities = [], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getAnimeDao(): AnimeDao
}