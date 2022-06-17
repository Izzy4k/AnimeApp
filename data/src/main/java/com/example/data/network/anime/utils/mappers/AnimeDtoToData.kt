package com.example.data.network.anime.utils.mappers

import com.example.core.base.BaseMapper
import com.example.data.network.anime.dto.Body
import com.example.domain.anime.entity.Anime

class AnimeDtoToData : BaseMapper<Body, Anime> {
    override fun invoke(body: Body): Anime = Anime.create(
        body.mal_id, body.images.jpg.large_image_url, body.title, body.genres[0].name
    )
}