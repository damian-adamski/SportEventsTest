package com.da.sporteventstest.presentation.mainScreen

import com.da.sporteventstest.utils.PageType

sealed interface MainAction {
    object RefreshStaticEvents : MainAction
    object StartPeriodicJob : MainAction
    object StopPeriodicJob : MainAction
    data class OnPageChange(val page: PageType) : MainAction
}