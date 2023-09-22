package com.da.sporteventstest.utils

import java.lang.Exception

sealed interface Resource {
    data class Loading(val isLoading: Boolean)
    data class Refreshing(val isRefreshing: Boolean)
    data class Error(val exception: Exception)
    data class Success<T>(val data: T)
}