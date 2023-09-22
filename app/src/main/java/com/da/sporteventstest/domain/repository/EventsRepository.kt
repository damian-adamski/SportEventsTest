package com.da.sporteventstest.domain.repository

import com.da.sporteventstest.domain.model.Event
import com.da.sporteventstest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    suspend fun getEvents(): Flow<Resource<List<Event>>>
}