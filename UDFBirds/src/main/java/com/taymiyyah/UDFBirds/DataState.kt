package com.taymiyyah.UDFBirds

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import retrofit2.Response

sealed class DataState<out DataStateType>() {
    data class Success<DataStateType>(val data: DataStateType?) : DataState<DataStateType>()
    data class Error(val message: String = "") : DataState<Nothing>()
    object Loading : DataState<Nothing>()
    object Idle : DataState<Nothing>()
}
