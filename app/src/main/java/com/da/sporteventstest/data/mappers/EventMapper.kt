package com.da.sporteventstest.data.mappers

import com.da.sporteventstest.data.network.dto.EventDto
import com.da.sporteventstest.domain.model.Event
import com.da.sporteventstest.utils.*

fun EventDto.mapToDomain(): Event {
    val localizedDate = this.date.localizeDateAndConvertToOdt()

    return Event(
        title = this.title,
        subtitle = this.subtitle,
        date = localizedDate,
        dateFormatted = localizedDate.convertDateToEventsFormattedString(),
        imageUrl = this.imageUrl,
        videoUrl = this.videoUrl
    )
}
