package com.example.data.network.manga.utils.mappers

import com.example.core.base.BaseMapper
import com.example.core.base.fromToList
import com.example.data.network.manga.dto.MangaDto
import com.example.domain.manga.entity.MangaPaging

class MangaDtoToPaging : BaseMapper<MangaDto, MangaPaging> {

    private val mangaDtoToData = MangaDtoToData()

    override fun invoke(p1: MangaDto): MangaPaging = MangaPaging.create(
        mangaDtoToData.fromToList(p1.body)!!,
        p1.pagination.current_page,
        p1.pagination.has_next_page
    )
}