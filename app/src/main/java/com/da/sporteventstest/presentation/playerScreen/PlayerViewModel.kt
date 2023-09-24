package com.da.sporteventstest.presentation.playerScreen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import com.da.sporteventstest.domain.model.video.VideoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    val player: Player
) : ViewModel() {

    var state by mutableStateOf(PlayerState())

    init {
        player.prepare()
    }

    fun onAction(action: PlayerAction) {
        when(action) {
            is PlayerAction.InitPlayer -> initPlayer(action.videoItem)
        }
    }

    private fun initPlayer(item: VideoItem) {
        state = state.copy(
            url = item.url,
            title = item.title,
            subtitle = item.subtitle
        )
        val mediaItem = MediaItem.fromUri(state.url)

        player.setMediaItem(mediaItem)
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}