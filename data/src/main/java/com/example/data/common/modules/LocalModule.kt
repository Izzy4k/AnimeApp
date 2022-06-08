package com.example.data.common.modules

import android.content.Context
import androidx.room.Room
import com.example.data.room.AppDataBase
import com.example.data.room.dao.AnimeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Volatile
    var INSTANCE: AppDataBase? = null

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase {
        val tempInstance = INSTANCE
        if (tempInstance != null) {
            return tempInstance
        }
        synchronized(this) {
            val instance =
                Room.databaseBuilder(context, AppDataBase::class.java, "anime_database")
                    .fallbackToDestructiveMigration()
                    .build()
            INSTANCE = instance
            return instance
        }
    }

    @Provides
    @Singleton
    fun provideAnimeDao(appDataBase: AppDataBase): AnimeDao = appDataBase.getAnimeDao()
}