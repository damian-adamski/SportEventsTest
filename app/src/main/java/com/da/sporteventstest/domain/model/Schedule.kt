package com.da.sporteventstest.domain.model

import java.time.OffsetDateTime

data class Schedule(
    val title : String,
    val subtitle : String,
    val date : OffsetDateTime,
    val dateFormatted: String,
    val imageUrl : String
)
