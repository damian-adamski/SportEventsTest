package com.da.sporteventstest.presentation.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.da.sporteventstest.R
import com.da.sporteventstest.domain.model.Event
import com.da.sporteventstest.domain.model.PeriodicEvent
import com.da.sporteventstest.presentation.ui.theme.AppColors
import java.time.OffsetDateTime

@Composable
fun EventItem(
    modifier: Modifier = Modifier,
    item: Event,
    onItemClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                item.videoUrl?.let { videoUrl ->
                    onItemClick(videoUrl)
                }
            }
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 2.dp)
                .padding(end = 4.dp)
        ) {
            val (imageRef, titleRef, dateRef) = createRefs()
            AsyncImage(
                modifier = Modifier
                    .constrainAs(imageRef){
                        start.linkTo(parent.start)
                    },
                model = item.imageUrl,
                contentScale = FixedScale(0.7f),
                contentDescription = item.title,
                placeholder = painterResource(id = R.drawable.placeholder)
            )

            Column(
                modifier = Modifier
                    .constrainAs(titleRef) {
                        start.linkTo(imageRef.end, 6.dp)
                        top.linkTo(imageRef.top)
                    }
            ) {
                Text(
                    text = item.title,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = item.subtitle,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            Text(
                modifier = Modifier
                    .constrainAs(dateRef) {
                        start.linkTo(imageRef.end, 6.dp)
                        bottom.linkTo(imageRef.bottom, 4.dp)
                    },
                text = item.dateFormatted,
                color = AppColors.TextSecondary,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventItemPreview() {
    val event = PeriodicEvent(
        title = "libero",
        subtitle = "bibendum",
        date = OffsetDateTime.now(),
        dateFormatted = "Tomorrow, 10:30",
        imageUrl = "",
        videoUrl = null

    )

    EventItem(
        item = event,
        onItemClick = { }
    )
}