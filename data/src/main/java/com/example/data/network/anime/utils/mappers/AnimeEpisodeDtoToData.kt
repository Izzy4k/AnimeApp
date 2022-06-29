package com.example.data.network.anime.utils.mappers

import com.example.core.base.BaseMapper
import com.example.data.network.anime.dto.BodyEpisode
import com.example.domain.anime.entity.AnimeEpisode

class AnimeEpisodeDtoToData : BaseMapper<BodyEpisode, AnimeEpisode> {
    override fun invoke(body: BodyEpisode): AnimeEpisode = AnimeEpisode.create(
        body.mal_id,body.title,body.aired
    )
}