package com.da.sporteventstest.presentation.mainScreen

import com.da.sporteventstest.domain.model.StaticEvent
import com.da.sporteventstest.domain.model.PeriodicEvent
import com.da.sporteventstest.utils.PageType

data class MainScreenState(
    val pageType: PageType = PageType.Events,
    val staticEvents: List<StaticEvent> = emptyList(),
    val periodicEvents: List<PeriodicEvent> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val exception: Exception? = null
) {
    val isScheduleEmpty: Boolean
        get() = periodicEvents.isEmpty()

    val isStaticEvents: Boolean
        get() = pageType == PageType.Events
}
