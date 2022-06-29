package com.example.data.local.manga.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnimeEntity(
    @PrimaryKey
    val id: Int = 0,
    val mal_id: Int,
    val title: String,
    val image: String,
    val author: String
)
