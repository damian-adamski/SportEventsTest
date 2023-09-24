package com.da.sporteventstest.domain.model.video

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoItem(
    val title: String,
    val subtitle: String,
    val url: String
) : Parcelable
