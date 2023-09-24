package com.da.sporteventstest.domain.model.exception

class StaticEventsException : Exception() {
    override val message: String
        get() = "An error occurred while fetching events."
}