package com.da.sporteventstest.presentation.eventsPage

sealed interface EventsPageAction {
    object Refresh: EventsPageAction
}