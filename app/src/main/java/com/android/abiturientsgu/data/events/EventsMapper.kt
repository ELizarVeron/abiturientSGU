package com.android.abiturientsgu.data.events

import com.android.abiturientsgu.data.events.locale.EventEntity
import com.android.abiturientsgu.data.events.remote.EventDto
import com.android.abiturientsgu.domain.models.Event

fun EventDto.toEvent(): Event {
    val themesList = themes.split(",").map {
        if (it[0] == ' ') it.drop(1) else it
    }

    return Event(
        id, eventName, date, themesList, place, annotation, status, record, review
    )
}


fun Event.toEventEntity(): EventEntity =
    EventEntity(
        id, eventName, date, themes.joinToString(separator = ","), place, annotation, status, record, review
    )


fun EventEntity.toEvent() =
    Event(id,  eventName, date,themes.split(",") , place, annotation, status, record, review)

