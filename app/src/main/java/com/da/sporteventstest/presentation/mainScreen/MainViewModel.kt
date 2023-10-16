package com.da.sporteventstest.presentation.mainScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.da.sporteventstest.domain.model.exception.PeriodicEventsException
import com.da.sporteventstest.domain.model.exception.StaticEventsException
import com.da.sporteventstest.domain.repository.EventsRepository
import com.da.sporteventstest.utils.PageType
import com.da.sporteventstest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val eventsRepository: EventsRepository
) : ViewModel() {

    var state by mutableStateOf(MainScreenState())

    private var getPeriodicJob: Job? = null

    init {
        getEvents()
    }

    fun onAction(action: MainAction) {
        when (action) {
            MainAction.StartPeriodicJob -> {
                // I'll start refreshing schedule from lifecycle only if onStart() is called
                // on SchedulePage, otherwise there's no point.
                // It will be handled inside changePage() method otherwise.
                // Bit of optimization - only starting when it's needed.
                if (state.pageType == PageType.Schedule) {
                    getSchedulePeriodic()
                }
                Log.wtf("lifecycle owner action", "started job")
            }
            MainAction.StopPeriodicJob -> {
                getPeriodicJob?.cancel()
                getPeriodicJob = null
                Log.wtf("lifecycle owner action", "stopped job")
            }
            MainAction.RefreshStaticEvents -> getEvents()
            is MainAction.OnPageChange -> changePage(action.page)
        }
    }

    private fun changePage(page: PageType) {
        if (page == PageType.Schedule) {
            getSchedulePeriodic()
        }
        state = state.copy(
            pageType = page
        )
    }

    private fun getEvents() {
        viewModelScope.launch {
            eventsRepository
                .getEvents()
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = resource.isLoading
                            )
                        }

                        is Resource.Error -> {
                            state = state.copy(
                                exception = throwException()
                            )
                        }

                        is Resource.Success -> {
                            state = state.copy(
                                staticEvents = resource.data,
                            )
                        }
                    }
                }
        }
    }

    private fun getSchedulePeriodic() {
        // to avoid launching on each page change
        if (getPeriodicJob?.isActive == true) {
            return
        }
        getPeriodicJob = viewModelScope.launch {
            Log.wtf("lifecycle owner action", "launched job")
            while (true) {
                eventsRepository
                    .getSchedule()
                    .collectLatest { resource ->
                        when (resource) {
                            is Resource.Loading -> {
                                if (state.isScheduleEmpty) {
                                    state = state.copy(
                                        isLoading = resource.isLoading
                                    )
                                }
                            }

                            is Resource.Error -> {
                                state = state.copy(
                                    exception = throwException()
                                )
                            }

                            is Resource.Success -> {
                                state = state.copy(
                                    periodicEvents = resource.data,
                                    isLoading = false
                                )
                            }
                        }
                    }
                delay(ScheduleTimePeriod)
            }
        }
    }

    private fun throwException(): Exception {
        return when(state.pageType) {
            PageType.Events -> StaticEventsException()
            PageType.Schedule -> PeriodicEventsException()
        }
    }

    companion object {
        private const val ScheduleTimePeriod = 5*1000L
    }

    override fun onCleared() {
        super.onCleared()
        getPeriodicJob?.cancel()
    }

}