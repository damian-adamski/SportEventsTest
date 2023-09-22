package com.da.sporteventstest.data.network

import com.da.sporteventstest.data.network.dto.EventDto
import retrofit2.http.GET

interface EventsApi {

    companion object {
        const val BASE_URL = "https://us-central1-dazn-sandbox.cloudfunctions.net"
    }

    @GET("/getEvents")
    fun getEvents(): List<EventDto>
}