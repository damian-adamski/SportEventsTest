package com.da.sporteventstest.data.repository

import com.da.sporteventstest.data.mappers.mapToDomain
import com.da.sporteventstest.data.network.EventsApi
import com.da.sporteventstest.domain.model.event.StaticEvent
import com.da.sporteventstest.domain.model.event.PeriodicEvent
import com.da.sporteventstest.domain.repository.EventsRepository
import com.da.sporteventstest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    val api: EventsApi,
) : EventsRepository {
    override suspend fun getEvents(): Flow<Resource<List<StaticEvent>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val events = api.getEvents().map {
                    it.mapToDomain()
                }.sortedBy { it.date }
                emit(Resource.Success(events))
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getSchedule(): Flow<Resource<List<PeriodicEvent>>> {
        return flow {
            try {
                val schedule = api.getSchedule().map {
                    it.mapToDomain()
                }.sortedBy { it.date }
                emit(Resource.Success(schedule))
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
            }
            emit(Resource.Loading(false))
        }
    }
}