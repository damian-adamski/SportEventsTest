package com.da.sporteventstest.core.di

import com.da.sporteventstest.data.network.EventsApi
import com.da.sporteventstest.data.repository.EventsRepositoryImpl
import com.da.sporteventstest.domain.repository.EventsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideEventsRepository(api: EventsApi): EventsRepository {
        return EventsRepositoryImpl(api)
    }
}