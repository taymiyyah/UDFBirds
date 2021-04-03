package com.taymiyyah.UDFBirds

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import retrofit2.Response


suspend fun <T> performDataBaseGetOperation(
    withLoadingState: Boolean = false,
    databaseQuery: suspend () -> T
): LiveData<DataState<T>> =
    liveData {
        if (withLoadingState) {
            emit(DataState.Loading)
            try {
                val response = databaseQuery()
                emit(DataState.Success(response))
            } catch (e: Exception) {
                emit(DataState.Error(e.message ?: e.toString()))
            }
        }
    }


suspend fun <T> performDataBaseGetOperationLiveData(
    withLoadingState: Boolean = false,
    databaseQuery: suspend () -> LiveData<T>
): LiveData<DataState<T>> =
    liveData {
        if (withLoadingState) {
            emit(DataState.Loading)
            try {
                val response: LiveData<DataState<T>> =
                    databaseQuery.invoke().map { DataState.Success(it) }
                emitSource(response)
            } catch (e: Exception) {
                emit(DataState.Error(e.message ?: e.toString()))
            }
        }
    }

