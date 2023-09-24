package com.da.sporteventstest.presentation.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.da.sporteventstest.presentation.ui.theme.AppColors

@Composable
fun PullToRefreshItem() {
    Box (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    vertical = 12.dp
                ),
            text = "Pull to refresh",
            color = AppColors.BottomBarColor,
            fontSize = 16.sp
        )
    }
}



@Preview
@Composable
private fun PullToRefreshItemPreview() {
    PullToRefreshItem()
}