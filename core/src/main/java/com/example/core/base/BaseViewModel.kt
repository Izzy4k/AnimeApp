package com.example.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

typealias StateResult<T, N> = StateFlow<BaseResult<T, N>>
typealias MutableStateResult<T, N> = MutableStateFlow<BaseResult<T, N>>
typealias SharedResult<T, N> = SharedFlow<BaseResult<T, N>>
typealias MutableSharedResult<T, N> = MutableSharedFlow<BaseResult<T, N>>


abstract class BaseViewModel : ViewModel() {

    private val _state = MutableStateFlow<BaseFragmentState>(BaseFragmentState.Init)
    val state: StateFlow<BaseFragmentState> get() = _state


    private fun showToast(message: String?) {
        _state.value = BaseFragmentState.ShowToast(message)
    }

    private fun hideLoading() {
        _state.value = BaseFragmentState.IsLoading(false)
    }

    private fun setLoading() {
        _state.value = BaseFragmentState.IsLoading(true)
    }

    /**
     *arguments listener for result from other fragments
     */
    open fun onResult(result: Any) {

    }

    sealed class BaseFragmentState {
        object Init : BaseFragmentState()
        data class IsLoading(val isLoading: Boolean) : BaseFragmentState()
        data class ShowToast(val message: String?) : BaseFragmentState()
    }
}