package com.example.data.network.anime.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.common.utils.mappers.BodyListDtoToListData
import com.example.data.network.anime.dto.AnimeDto
import com.example.domain.anime.entity.Anime

typealias AnimePageLoader = suspend (page: Int) -> AnimeDto

class AnimePagingSource(
    private val loader: AnimePageLoader,
    private val pageSize: Int
) : PagingSource<Int, Anime>() {

    private val bodyListDtoToListData = BodyListDtoToListData()

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        val anchorPos = state.anchorPosition ?: return null

        val page = state.closestPageToPosition(anchorPos) ?: return null

        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val pageIndex = params.key ?: 0
        return try {
            val animeLoader = loader.invoke(pageIndex)
            val list = bodyListDtoToListData.invoke(animeLoader.body)
            return LoadResult.Page(
                data = list,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (animeLoader.body.size == params.loadSize)
                    pageIndex + (params.loadSize / pageSize) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}