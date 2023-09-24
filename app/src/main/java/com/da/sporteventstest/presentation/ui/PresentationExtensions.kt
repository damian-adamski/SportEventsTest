package com.da.sporteventstest.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.da.sporteventstest.presentation.ui.theme.AppValues

fun Modifier.conditional(condition: Boolean, modifier: Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

fun Modifier.bottomBarPadding() =
    this.padding(bottom = AppValues.BottomBarHeight)