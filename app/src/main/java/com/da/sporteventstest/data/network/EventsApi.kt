package com.da.sporteventstest.data.network

import com.da.sporteventstest.data.network.dto.EventDto
import com.da.sporteventstest.data.network.dto.ScheduleDto
import retrofit2.http.GET

interface EventsApi {

    companion object {
        const val BASE_URL = "https://us-central1-dazn-sandbox.cloudfunctions.net"
    }

    @GET("/getEvents")
    suspend fun getEvents(): List<EventDto>

    @GET("/getSchedule")
    suspend fun getSchedule(): List<ScheduleDto>
}