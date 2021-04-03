package com.taymiyyah.UDFBirds

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import retrofit2.Response


suspend fun <T> getNetworkResult(networkCall: suspend () -> Response<T>): DataState<T> {
    try {
        val response = networkCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return DataState.Success(body)
        }
        return DataState.Error(" ${response.code()} ${response.message()}")
    } catch (e: Exception) {
        return DataState.Error(e.message ?: e.toString())
    }
}

fun <T> performNetworkCallOperation(networkCall: suspend () -> Response<T>): LiveData<DataState<T>> =
    liveData(Dispatchers.IO) {
        emit(DataState.Loading)
        emit(getNetworkResult(networkCall))
    }

fun <T, A> performDBAndNetworkOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Response<A>,
    whereToSaveNewDataDao: suspend (A) -> Unit = {}
): LiveData<DataState<T>> =
    liveData(Dispatchers.IO) {
        emit(DataState.Loading)

        val responseStatus = getNetworkResult<A>(networkCall)
        if (responseStatus is DataState.Success) {
            responseStatus.data?.let { whereToSaveNewDataDao(it) }
            if (responseStatus is DataState.Error) {
                emit(DataState.Error(responseStatus.message))
            }
        }
        //cache is always the source of the truth (just in code)
        val cache: LiveData<DataState<T>> = databaseQuery.invoke().map { DataState.Success(it) }
        emitSource(cache)

    }


