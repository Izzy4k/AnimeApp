package com.example.data.network.manga.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.manga.entity.Manga
import com.example.domain.manga.entity.MangaPaging


typealias MangaPageLoader = suspend (page: Int, limit: Int) -> MangaPaging

class MangaPagingSource(
    private val mangaPageLoader: MangaPageLoader,
    private val limit: Int
) : PagingSource<Int, Manga>() {
    override fun getRefreshKey(state: PagingState<Int, Manga>): Int? {
        val anchorPos = state.anchorPosition ?: return null

        val page = state.closestPageToPosition(anchorPos) ?: return null

        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Manga> {
        val pageIndex = params.key ?: 0
        return try {
            val mangaLoader = mangaPageLoader.invoke(pageIndex, limit)
            return LoadResult.Page(
                data = mangaLoader.manga,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (mangaLoader.has_next_page) pageIndex + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}