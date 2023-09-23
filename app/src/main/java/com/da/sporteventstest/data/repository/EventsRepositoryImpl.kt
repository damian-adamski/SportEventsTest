package com.da.sporteventstest.data.repository

import com.da.sporteventstest.data.mappers.mapToDomain
import com.da.sporteventstest.data.network.EventsApi
import com.da.sporteventstest.domain.model.Event
import com.da.sporteventstest.domain.model.Schedule
import com.da.sporteventstest.domain.repository.EventsRepository
import com.da.sporteventstest.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.replay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    val api: EventsApi,
) : EventsRepository {
    override suspend fun getEvents(): Flow<Resource<List<Event>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val events = api.getEvents().map {
                    it.mapToDomain()
                }
                emit(Resource.Success(events))
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getSchedule(isShowLoading: Boolean): Flow<Resource<List<Schedule>>> {
        return flow {
            try {
                val schedule = api.getSchedule().map {
                    it.mapToDomain()
                }
                emit(Resource.Success(schedule))
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
            }
            emit(Resource.Loading(false))
        }
    }
}