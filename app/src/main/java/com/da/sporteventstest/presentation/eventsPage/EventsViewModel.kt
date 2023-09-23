package com.da.sporteventstest.presentation.eventsPage

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.da.sporteventstest.domain.repository.EventsRepository
import com.da.sporteventstest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsRepository: EventsRepository
) : ViewModel() {

    var state by mutableStateOf(EventsPageState())

    init {
        getSportEvents()
    }

    fun onAction(action: EventsPageAction) {
        when (action) {
            EventsPageAction.Refresh -> getSportEvents()
        }
    }

    // TODO fix Flow handling
    fun getSportEvents() {
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
                            // TODO
                        }

                        is Resource.Success -> {
                            state = state.copy(
                                events = resource.data
                            )
                        }

                        else -> {}
                    }
                }

        }
    }

    private fun setLoadingState(isLoading: Boolean) {
        state = state.copy(
            isLoading = isLoading
        )
    }
}