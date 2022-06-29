package com.example.animeapp.ui.fragment.detail

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import com.example.domain.anime.entity.Anime
import com.example.domain.anime.use_case.ActionAnimeEntityUseCase
import com.example.domain.anime.use_case.CheckExistsAnimeEntityUseCase
import com.example.domain.anime.use_case.GetAnimeInfoUseCase
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
class DetailViewModel @Inject constructor(
    private val checkExistsAnimeEntityUseCase: CheckExistsAnimeEntityUseCase,
    private val actionAnimeEntityUseCase: ActionAnimeEntityUseCase,
    private val getAnimeInfoUseCase: GetAnimeInfoUseCase
) :
    BaseViewModel() {

    private val _checkExistsAnime = MutableStateFlow(false)
    val checkExistsAnime: StateFlow<Boolean> get() = _checkExistsAnime


    fun checkExistsForAnime(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            checkExistsAnimeEntityUseCase.checkExistsAnimeEntity(id).collect {
                _checkExistsAnime.value = it
            }
        }
    }

    fun actionAnime(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            actionAnimeEntityUseCase.actionAnimeEntity(
                getAnimeInfoUseCase.getAnimeInfo(id)
                    .stateIn(
                        viewModelScope
                    ).value, id, checkExistsAnime.value
            )
        }
    }

    suspend fun getAnimeInfo(id: Int): StateFlow<Anime> {
        return getAnimeInfoUseCase.getAnimeInfo(id).stateIn(
            viewModelScope
        )
    }


}