package com.da.sporteventstest.utils

import androidx.annotation.DrawableRes
import com.da.sporteventstest.R

enum class PageType(
    val title: String,
    @DrawableRes val icon: Int
) {
    Events("Events", R.drawable.baseline_event_48),
    Schedule("PeriodicEvent", R.drawable.baseline_schedule_48)
}