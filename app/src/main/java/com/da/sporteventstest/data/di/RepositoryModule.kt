package com.da.sporteventstest.data.di

import com.da.sporteventstest.data.repository.EventsRepositoryImpl
import com.da.sporteventstest.domain.repository.EventsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindEventsRepository(
        eventsRepositoryImpl: EventsRepositoryImpl
    ): EventsRepository
}