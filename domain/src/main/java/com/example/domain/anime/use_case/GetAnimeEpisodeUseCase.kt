package com.example.domain.anime.use_case

import androidx.paging.PagingData
import com.example.domain.anime.entity.AnimeEpisode
import com.example.domain.anime.repo.AnimeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeEpisodeUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend fun getAnimeEpisode(id: Int): Flow<PagingData<AnimeEpisode>> =
        repository.getAnimeEpisode(id)
}