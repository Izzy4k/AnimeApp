package com.example.data.network.manga.utils.mappers

import com.example.core.base.BaseMapper
import com.example.data.network.manga.dto.Body
import com.example.domain.manga.entity.Manga

class MangaDtoToData : BaseMapper<Body, Manga> {
    override fun invoke(p1: Body): Manga = Manga.create(
        p1.mal_id,p1.images.jpg.large_image_url,p1.title,p1.authors[0].name
    )
}