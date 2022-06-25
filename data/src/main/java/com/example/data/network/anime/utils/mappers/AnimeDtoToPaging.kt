package com.example.data.network.anime.utils.mappers

import com.example.core.base.BaseMapper
import com.example.core.utils.fromToList
import com.example.data.network.anime.dto.AnimeDto
import com.example.domain.anime.entity.AnimePaging

class AnimeDtoToPaging : BaseMapper<AnimeDto, AnimePaging> {

    private val animeMapper = AnimeDtoToData()

    override fun invoke(anime: AnimeDto): AnimePaging =
        AnimePaging.create(
            animeMapper.fromToList(anime.body),
            anime.pagination.current_page,
            anime.pagination.has_next_page
        )
}