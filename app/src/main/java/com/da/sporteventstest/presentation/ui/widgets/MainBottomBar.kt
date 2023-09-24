package com.da.sporteventstest.presentation.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.da.sporteventstest.presentation.ui.theme.AppColors
import com.da.sporteventstest.presentation.ui.theme.AppValues
import com.da.sporteventstest.utils.PageType

@Composable
fun MainBottomBar(
    selectedPage: PageType,
    onItemSelected: (PageType) -> Unit
) {
    BottomNavigation(
        modifier = Modifier
            .height(AppValues.BottomBarHeight),
        backgroundColor = AppColors.BottomBarColor,
        elevation = 5.dp
    ) {
        PageType.values().forEach { page ->

            val isSelected = page == selectedPage
            val (iconTint, background) = if (isSelected)
                Pair(AppColors.BottomBarColor, AppColors.BackgroundColor)
            else
                Pair(AppColors.BackgroundColor, AppColors.BottomBarColor)

            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    onItemSelected(page)
                },
                icon = {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(background)
                    ) {

                        Icon(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(32.dp)
                                .background(Color.Transparent),
                            painter = painterResource(id = page.icon),
                            contentDescription = page.title,
                            tint = iconTint
                        )
                    }
                },
                label = {
                    Text(
                        modifier = Modifier,
                        text = page.title,
                        color = AppColors.BackgroundColor,
                        fontSize = 12.sp
                    )
                },
                selectedContentColor = AppColors.BackgroundColor,
                unselectedContentColor = AppColors.BackgroundColor
            )
        }
    }
}