package com.da.sporteventstest.presentation.eventsPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.da.sporteventstest.presentation.eventsPage.widgets.EventItem
import com.da.sporteventstest.presentation.theme.TextPrimary
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Destination
fun EventsListPage(
    navigator: DestinationsNavigator,
    viewModel: EventsViewModel = hiltViewModel()
) {
    val state = viewModel.state

    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewModel.state.isLoading,
        onRefresh = { viewModel.onAction(EventsPageAction.Refresh) }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(state.events.size) {
                val event = state.events[it]
                EventItem(
                    modifier = Modifier
                        .padding(top = 2.dp),
                    event = event
                )
                if (it < state.events.size) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        thickness = 2.dp,
                        color = TextPrimary
                    )
                }
            }
        }
    }
}