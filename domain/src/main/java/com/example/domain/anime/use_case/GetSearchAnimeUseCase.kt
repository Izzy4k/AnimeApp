package com.example.domain.anime.use_case

import androidx.paging.PagingData
import com.example.domain.anime.entity.Anime
import com.example.domain.anime.repo.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchAnimeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
   suspend fun getSearchAnime(searchBy: String): Flow<PagingData<Anime>> =
        repository.getSearchAnime(searchBy)
}