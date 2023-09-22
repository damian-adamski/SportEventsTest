package com.da.sporteventstest.data.repository

import com.da.sporteventstest.data.mappers.mapToDomain
import com.da.sporteventstest.data.network.EventsApi
import com.da.sporteventstest.domain.model.Event
import com.da.sporteventstest.domain.repository.EventsRepository
import com.da.sporteventstest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    val api: EventsApi,
) : EventsRepository {
    override suspend fun getEvents(): Flow<Resource<List<Event>>> {
        return flow {
            emit(Resource.Loading(true))
            val result = try {
                api.getEvents()
            } catch (ex: Exception) {
                emit(Resource.Error(ex))
                null
            }
            result?.let { events ->
                emit(Resource.Success(
                    events.map { it.mapToDomain() }
                ))
            }
            emit(Resource.Loading(false))
        }
    }
}