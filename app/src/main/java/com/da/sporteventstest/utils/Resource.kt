package com.da.sporteventstest.utils

import java.lang.Exception

sealed class Resource<T> {
    data class Loading<T>(val isLoading: Boolean): Resource<T>()
    data class Error<T>(val exception: Exception): Resource<T>()
    data class Success<T>(val data: T): Resource<T>()
}