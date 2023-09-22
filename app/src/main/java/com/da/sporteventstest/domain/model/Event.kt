package com.da.sporteventstest.domain.model

import com.google.gson.annotations.SerializedName
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

data class Event(
    var title : String,
    var subtitle : String,
    var date : OffsetDateTime,
    var dateFormatted: String,
    var imageUrl : String,
    var videoUrl : String
) {


}
