package com.da.sporteventstest.presentation.playerScreen

import com.da.sporteventstest.domain.model.video.VideoItem

sealed interface PlayerAction {
    data class InitPlayer(val videoItem: VideoItem): PlayerAction
}