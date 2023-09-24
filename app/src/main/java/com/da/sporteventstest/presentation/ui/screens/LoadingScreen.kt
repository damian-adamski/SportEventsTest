package com.da.sporteventstest.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.da.sporteventstest.presentation.ui.theme.AppColors

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement
                .spacedBy(4.dp)
        ) {
            CircularProgressIndicator(
                color = AppColors.BottomBarColor,
                strokeWidth = 3.dp
            )
            Text(
                text = "Please wait for events to load... ",
                color = AppColors.BottomBarColor,
                fontSize = 16.sp
            )
        }
    }
}