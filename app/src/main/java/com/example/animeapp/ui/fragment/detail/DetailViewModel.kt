package com.example.animeapp.ui.fragment.detail

import androidx.lifecycle.viewModelScope
import com.example.core.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor() :
    BaseViewModel() {


    fun getAnime(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    fun getManga(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

}