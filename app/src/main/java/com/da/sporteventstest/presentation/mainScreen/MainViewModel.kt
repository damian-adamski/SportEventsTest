package com.da.sporteventstest.presentation.mainScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.da.sporteventstest.domain.model.exception.StaticEventsException
import com.da.sporteventstest.domain.model.exception.PeriodicEventsException
import com.da.sporteventstest.domain.repository.EventsRepository
import com.da.sporteventstest.utils.PageType
import com.da.sporteventstest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val eventsRepository: EventsRepository
) : ViewModel() {

    var state by mutableStateOf(MainScreenState())

    init {
        getEvents()
    }

    fun onAction(action: MainAction) {
        when (action) {
            MainAction.RefreshStaticEvents -> getEvents()
            is MainAction.OnPageChange -> changePage(action.page)
            is MainAction.OnVideoLaunch -> TODO()

        }
    }

    private fun changePage(page: PageType) {
        if (state.isScheduleEmpty) {
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
                                isRefreshing = false
                            )
                        }
                    }
                }
        }
    }

    private fun getSchedulePeriodic() {
        viewModelScope.launch {
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

    private fun startRefreshing() {
        state = state.copy(
            isRefreshing = true
        )
    }

    private fun throwException(): Exception {
        return when(state.pageType) {
            PageType.Events -> StaticEventsException()
            PageType.Schedule -> PeriodicEventsException()
        }
    }

    companion object {
        private const val ScheduleTimePeriod = 15*1000L
    }

}