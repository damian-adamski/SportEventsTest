package com.da.sporteventstest.domain.model

import java.time.OffsetDateTime

interface Event {
    var title: String
    var subtitle: String
    var date: OffsetDateTime
    var dateFormatted: String
    var imageUrl: String
    var videoUrl: String?
}
