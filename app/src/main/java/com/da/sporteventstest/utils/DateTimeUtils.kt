package com.da.sporteventstest.utils

import java.time.LocalDateTime
import java.time.Month
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun String.localizeDateAndConvertToOdt(): OffsetDateTime {
    val localOffset = OffsetDateTime.now().offset

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val instant = LocalDateTime.parse(this, formatter)
        .atZone(ZoneId.of("UTC"))
        .toInstant()
    val formattedOdt = OffsetDateTime.ofInstant(instant, ZoneOffset.UTC)

    return formattedOdt.withOffsetSameInstant(localOffset)
}

fun OffsetDateTime.convertDateToEventsFormattedString(): String {
    val today = OffsetDateTime.now().truncatedTo(ChronoUnit.DAYS)

    return when {
        this.isEqual(today) -> "Today"
        this.plusDays(1).isEqual(today) -> "Yesterday"
        this.minusDays(1).isEqual(today) -> "Tomorrow"
        else -> this.mapToString()
    }
}

/**
 * Assuming that there are no past days.
 */
@Throws(IllegalArgumentException::class)
fun OffsetDateTime.convertDateToScheduleFormattedString(): String {
    val today = OffsetDateTime.now()

    if (today.isAfter(this)) throw IllegalArgumentException()

    val daysUntil = ChronoUnit.DAYS.between(today, this)

    return when(daysUntil) {
        0L -> "Today, ${this.hour}:${this.minute}"
        1L -> "Tomorrow, ${this.hour}:${this.minute}"
        in 2..9 -> "In ${daysUntil.getWordForDayNumber()} days"
        else -> "In ${daysUntil.toInt()} days"
    }
}

/**
 * Don't know better way to do this and it is too much effort,
 * so I made it to 9.
 * @return String
 */
@Throws(IllegalArgumentException::class)
private fun Long.getWordForDayNumber(): String {
    val words = listOf(
        "two","three","four","five","six","seven","eight","nine"
    )
    if (this !in (2..9)) throw IllegalArgumentException()

    return words[this.toInt()]
}

private fun OffsetDateTime.mapToString(): String {
    return "$dayOfMonth.${month.reformatToTwoDigits()}.$year"
}

private fun Month.reformatToTwoDigits(): String {
     return when(val value = this.value) {
         in (1..9) -> "0$value"
         else -> this.value.toString()
     }
}