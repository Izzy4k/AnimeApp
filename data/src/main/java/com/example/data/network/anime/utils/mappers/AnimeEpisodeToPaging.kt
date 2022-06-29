package com.example.data.network.anime.utils.mappers

import com.example.core.base.BaseMapper
import com.example.core.base.fromToList
import com.example.data.network.anime.dto.AnimeEpisodeDto
import com.example.domain.anime.entity.AnimeEpisodePaging

class AnimeEpisodeToPaging : BaseMapper<AnimeEpisodeDto, AnimeEpisodePaging> {

    private val animeEpisodeDtoToData = AnimeEpisodeDtoToData()

    override fun invoke(anime: AnimeEpisodeDto): AnimeEpisodePaging = AnimeEpisodePaging.create(
        animeEpisodeDtoToData.fromToList(anime.body)!!, anime.pagination.current_page,
        anime.pagination.has_next_page
    )
}