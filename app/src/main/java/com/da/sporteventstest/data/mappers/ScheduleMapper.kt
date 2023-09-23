package com.da.sporteventstest.data.mappers

import com.da.sporteventstest.data.network.dto.ScheduleDto
import com.da.sporteventstest.domain.model.Schedule
import com.da.sporteventstest.utils.convertDateToScheduleFormattedString
import com.da.sporteventstest.utils.localizeDateAndConvertToOdt

fun ScheduleDto.mapToDomain(): Schedule {
    val date = this.date.localizeDateAndConvertToOdt()

    return Schedule(
        title = this.title,
        subtitle = this.subtitle,
        date = date,
        dateFormatted = date.convertDateToScheduleFormattedString(),
        imageUrl = this.imageUrl
    )
}