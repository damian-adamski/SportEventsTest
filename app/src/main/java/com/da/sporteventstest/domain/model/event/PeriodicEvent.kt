package com.da.sporteventstest.domain.model.event

import com.da.sporteventstest.domain.model.event.Event
import java.time.OffsetDateTime

data class PeriodicEvent(
    override var title : String,
    override var subtitle : String,
    override var date : OffsetDateTime,
    override var dateFormatted: String,
    override var imageUrl : String,
    override var videoUrl : String? = null
) : Event
