package com.da.sporteventstest.presentation.mainScreen

import com.da.sporteventstest.utils.PageType

sealed interface MainAction {
    object RefreshStaticEvents : MainAction
    data class OnPageChange(val page: PageType) : MainAction
    data class OnVideoLaunch(val videoUrl: String) : MainAction
}