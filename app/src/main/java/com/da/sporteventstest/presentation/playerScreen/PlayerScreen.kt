package com.da.sporteventstest.presentation.playerScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.ui.PlayerView
import com.da.sporteventstest.domain.model.video.VideoItem
import com.da.sporteventstest.presentation.ui.theme.AppColors
import com.ramcosta.composedestinations.annotation.Destination


@Composable
@Destination
fun PlayerScreen(
    videoItem: VideoItem,
    viewModel: PlayerViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(true) {
        viewModel.onAction(PlayerAction.InitPlayer(videoItem))
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = state.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.TextPrimary
            )
            Text(
                text = state.subtitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = AppColors.TextPrimary
            )
        }
        AndroidView(
            factory = { context ->
                PlayerView(context).also {
                    it.player = viewModel.player
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
        )
    }

}