package com.egabruskiy.curiositywatcher.data.model

import java.io.IOException

sealed class ResourceResult<out T> {

    data class Success<T>(val data: T) : ResourceResult<T>()

    data class Error(val exception: Throwable) : ResourceResult<Nothing>() {
        val isNetworkError: Boolean get() = exception is IOException
    }

    object Empty : ResourceResult<Nothing>()

    object Loading : ResourceResult<Nothing>()


    companion object{
        fun loading() = Loading

        fun <T> successOrEmpty(list: MutableList<T>): ResourceResult<MutableList<T>> {
            return if (list.isEmpty()) Empty else Success(list)
        }

        fun error(exception: Throwable) = Error(exception)

    }

}