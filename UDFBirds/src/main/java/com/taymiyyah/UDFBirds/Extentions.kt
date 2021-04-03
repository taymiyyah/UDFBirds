package com.taymiyyah.UDFBirds


fun <T> DataState<T>.onError(block: DataState.Error.() -> Unit): DataState<T> {
    if (this is DataState.Error) {
        block()
    }
    return this

}

fun <T> DataState<T>.onSuccess(block: DataState.Success<T>.() -> Unit): DataState<T> {
    if (this is DataState.Success) {
        block()
    }
    return this
}

fun <T> DataState<T>.onLoading(block: () -> Unit): DataState<T> {
    if (this is DataState.Loading) {
        block()
    }
    return this
}

fun <T> DataState<T>.onIdle(block: () -> Unit): DataState<T> {
    if (this is DataState.Idle) {
        block()
    }
    return this

}