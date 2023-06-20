package com.github.zsoltk.pokedex.common

import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.remember
import androidx.compose.state
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


sealed class AsyncState<T> {
    class Initialised<T>: AsyncState<T>()
    class Loading<T>: AsyncState<T>()
    data class Error<T>(val error: Throwable): AsyncState<T>()
    data class Result<T>(val result: T): AsyncState<T>()
}


@Composable
fun <T> observe(data: LiveData<T>): T? {
    var result by state { data.value }
    val observer = remember { Observer<T> { result = it } }

    onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }

    return result
}
