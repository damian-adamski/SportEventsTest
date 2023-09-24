package com.da.sporteventstest.data.mappers

import com.da.sporteventstest.data.network.dto.EventDto
import com.da.sporteventstest.domain.model.StaticEvent
import com.da.sporteventstest.utils.*

fun EventDto.mapToDomain(): StaticEvent {
    val localizedDate = this.date.localizeDateAndConvertToOdt()

    return StaticEvent(
        title = this.title,
        subtitle = this.subtitle,
        date = localizedDate,
        dateFormatted = localizedDate.convertDateToEventsFormattedString(),
        imageUrl = this.imageUrl,
        videoUrl = this.videoUrl
    )
}
