package com.da.sporteventstest.presentation.eventsPage

import com.da.sporteventstest.domain.model.Event

data class EventsPageState(
    val events: List<Event> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
)
