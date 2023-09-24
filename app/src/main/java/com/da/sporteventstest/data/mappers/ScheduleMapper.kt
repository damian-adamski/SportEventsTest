package com.da.sporteventstest.data.mappers

import com.da.sporteventstest.data.network.dto.ScheduleDto
import com.da.sporteventstest.domain.model.event.PeriodicEvent
import com.da.sporteventstest.utils.convertDateToScheduleFormattedString
import com.da.sporteventstest.utils.localizeDateAndConvertToOdt

fun ScheduleDto.mapToDomain(): PeriodicEvent {
    val date = this.date.localizeDateAndConvertToOdt()

    return PeriodicEvent(
        title = this.title,
        subtitle = this.subtitle,
        date = date,
        dateFormatted = date.convertDateToScheduleFormattedString(),
        imageUrl = this.imageUrl
    )
}