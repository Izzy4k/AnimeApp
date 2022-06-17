package com.example.data.network.anime.utils.mappers

import com.example.core.base.BaseMapper
import com.example.data.network.anime.dto.Body
import com.example.domain.anime.entity.Anime

class BodyListDtoToListData : BaseMapper<List<Body>, List<Anime>> {

    private val animeDtoToData = AnimeDtoToData()

    override fun invoke(list: List<Body>): List<Anime> {
        val animeList = arrayListOf<Anime>()
        for (item in list) {
            animeList.add(animeDtoToData.invoke(item))
        }
        return animeList
    }
}