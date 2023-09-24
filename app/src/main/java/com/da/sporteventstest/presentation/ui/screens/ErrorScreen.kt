package com.da.sporteventstest.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.da.sporteventstest.domain.model.exception.PeriodicEventsException
import com.da.sporteventstest.presentation.ui.theme.AppColors
import java.lang.Exception

@Composable
fun ErrorScreen(
    ex: Exception?,
    onReloadPageClick: () -> Unit
) {
    val message = ex?.message ?: "Unexpected error occurred"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement
                .spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = message,
                color = AppColors.BottomBarColor,
                fontSize = 16.sp
            )
            Button(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(AppColors.BottomBarColor),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.BottomBarColor
                ),
                onClick = onReloadPageClick
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                    text = "Reload page",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(PeriodicEventsException()) {

    }
}