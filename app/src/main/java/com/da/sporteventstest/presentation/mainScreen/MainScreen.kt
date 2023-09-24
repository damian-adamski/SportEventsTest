package com.da.sporteventstest.presentation.mainScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.da.sporteventstest.domain.model.event.Event
import com.da.sporteventstest.domain.model.video.VideoItem
import com.da.sporteventstest.presentation.destinations.PlayerScreenDestination
import com.da.sporteventstest.presentation.ui.bottomBarPadding
import com.da.sporteventstest.presentation.ui.conditional
import com.da.sporteventstest.presentation.ui.screens.LoadingScreen
import com.da.sporteventstest.presentation.ui.widgets.EventItem
import com.da.sporteventstest.presentation.ui.theme.AppColors
import com.da.sporteventstest.presentation.ui.widgets.MainBottomBar
import com.da.sporteventstest.presentation.ui.widgets.PullToRefreshItem
import com.da.sporteventstest.utils.PageType
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
@Destination
@RootNavGraph(start = true)
fun MainScreen(
    navigator: DestinationsNavigator,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state = viewModel.state

    val events = when (state.pageType) {
        PageType.Events -> state.staticEvents
        PageType.Schedule -> state.periodicEvents
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.onAction(MainAction.RefreshStaticEvents) }
    )

    Scaffold(
        bottomBar = {
            MainBottomBar(
                selectedPage = state.pageType,
                onItemSelected = { page ->
                    viewModel.onAction(MainAction.OnPageChange(page))
                }
            )
        }
    ) {

        Box(modifier = Modifier
            .bottomBarPadding()
        ) {
            when {
                state.isLoading -> LoadingScreen()
                else -> ScreenContent(
                    events = events,
                    isStaticDisplay = state.isStaticEvents,
                    pullRefreshState = pullRefreshState,
                    onStaticItemClick = { videoItem ->
                        navigator.navigate(PlayerScreenDestination(videoItem))
                    }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ScreenContent(
    events: List<Event>,
    isStaticDisplay: Boolean,
    pullRefreshState: PullRefreshState,
    onStaticItemClick: (VideoItem) -> Unit
) {
    val lazyColumnState = rememberLazyListState()

    SideEffect {
        CoroutineScope(Dispatchers.Main).launch {
            lazyColumnState.scrollToItem(1, 0)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .conditional(isStaticDisplay) {
                Modifier
                    .pullRefresh(pullRefreshState)
            }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyColumnState
        ) {
            val size = events.size

            if (isStaticDisplay) {
                item { PullToRefreshItem() }
            }

            items(size) {
                val item = events[it]
                EventItem(
                    modifier = Modifier
                        .padding(top = 2.dp),
                    item = item,
                    onItemClick = { videoItem ->
                        onStaticItemClick(videoItem)
                    }
                )
                if (it < size) {
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        thickness = 2.dp,
                        color = AppColors.TextPrimary
                    )
                }
            }
        }
        PullRefreshIndicator(isStaticDisplay, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}