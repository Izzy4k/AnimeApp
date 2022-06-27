package com.example.animeapp.ui.fragment.manga

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.core.base.BaseViewModel
import com.example.core.base.ErrorResult
import com.example.core.base.PendingResult
import com.example.core.base.SuccessResult
import com.example.domain.manga.entity.Manga
import com.example.domain.manga.use_case.GetRandomMangaUseCase
import com.example.domain.manga.use_case.GetTopMangaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class MangaViewModel @Inject constructor(
    private val getRandomMangaUseCase: GetRandomMangaUseCase,
    private val getTopMangaUseCase: GetTopMangaUseCase
) : BaseViewModel() {
    private val _mangaRandom = MutableStateFlow<List<Manga>>(mutableListOf())
    val mangaRandom: StateFlow<List<Manga>> get() = _mangaRandom


    val mangaFlow: Flow<PagingData<Manga>>

    private val searchBy = MutableLiveData("")

    init {
        mangaFlow = searchBy.asFlow()
            .debounce(500)
            .flatMapLatest {
                getTopMangaUseCase.getTopManga(it)
            }
            .cachedIn(viewModelScope)
    }

    fun getRandom() {
        viewModelScope.launch(Dispatchers.IO) {
            getRandomMangaUseCase.getRandomManga()
                .onStart {
                    setLoading()
                }.catch {
                    hideLoading()
                }.collect {
                    when (it) {
                        is ErrorResult -> hideLoading()
                        PendingResult -> setLoading()
                        is SuccessResult -> {
                            hideLoading()
                            _mangaRandom.value = it.data
                        }
                    }
                }
        }
    }

    fun setSearchBy(value: String) {
        if (searchBy.value == value) return
        searchBy.value = value
    }
}