package com.da.sporteventstest.domain.model

import java.time.OffsetDateTime

data class StaticEvent(
    override var title : String,
    override var subtitle : String,
    override var date : OffsetDateTime,
    override var dateFormatted: String,
    override var imageUrl : String,
    override var videoUrl : String?
) : Event
