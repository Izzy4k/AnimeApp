package com.example.data.common.base

import com.example.core.base.ErrorResult
import com.example.core.base.PendingResult
import com.example.core.base.SuccessResult
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {

    protected fun <T : Any> doRequest(request: suspend () -> T) = flow {
        emit(PendingResult)
        try {
            emit(SuccessResult(data = request()))
        } catch (ioException: Exception) {
            ioException.printStackTrace()
            emit(
                ErrorResult(
                    ioException.localizedMessage ?: "Error Occurred!"
                )
            )
        }
    }
}