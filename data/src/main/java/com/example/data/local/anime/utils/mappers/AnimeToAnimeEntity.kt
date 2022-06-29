package com.example.data.local.anime.utils.mappers

import com.example.core.base.BaseMapper
import com.example.data.local.anime.entities.AnimeEntity
import com.example.domain.anime.entity.Anime

class AnimeToAnimeEntity : BaseMapper<Anime,AnimeEntity> {
    override fun invoke(p1: Anime): AnimeEntity  = AnimeEntity(
        mal_id = p1.id, title = p1.title, image = p1.image, genre = p1.genre
    )
}