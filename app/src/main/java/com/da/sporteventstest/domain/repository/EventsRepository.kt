package com.da.sporteventstest.domain.repository

import com.da.sporteventstest.domain.model.event.StaticEvent
import com.da.sporteventstest.domain.model.event.PeriodicEvent
import com.da.sporteventstest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    suspend fun getEvents(): Flow<Resource<List<StaticEvent>>>
    suspend fun getSchedule(): Flow<Resource<List<PeriodicEvent>>>
}