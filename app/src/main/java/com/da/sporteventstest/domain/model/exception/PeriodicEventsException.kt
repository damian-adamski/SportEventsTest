package com.da.sporteventstest.domain.model.exception

class PeriodicEventsException : Exception() {
    override val message: String
        get() = "An error occurred while fetching schedule."
}