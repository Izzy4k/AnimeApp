package com.example.data.local.anime.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.common.utils.Anime

@Entity(tableName = Anime.name)
data class AnimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val image: String,
    val genre: String
)
