package com.example.data.network.anime.utils.mappers

import com.example.core.base.BaseMapper
import com.example.data.network.anime.dto.AnimeDto
import com.example.domain.anime.entity.AnimePaging

class AnimeDtoToPaging : BaseMapper<AnimeDto, AnimePaging> {

    private val bodyListDtoToListData = BodyListDtoToListData()
    override fun invoke(anime: AnimeDto): AnimePaging =
        AnimePaging.create(
            bodyListDtoToListData.invoke(anime.body), anime.pagination.current_page,
            anime.pagination.has_next_page
        )
}