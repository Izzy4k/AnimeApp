package com.example.data.local.anime.utils.mappers

import com.example.core.base.BaseMapper
import com.example.data.local.anime.entities.AnimeEntity
import com.example.domain.anime.entity.Anime

class AnimeEntityToAnime : BaseMapper<AnimeEntity, Anime> {
    override fun invoke(p1: AnimeEntity): Anime =
        Anime.create(p1.mal_id, p1.image, p1.title, p1.genre)
}