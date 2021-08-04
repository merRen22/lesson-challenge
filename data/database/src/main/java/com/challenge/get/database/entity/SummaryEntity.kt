package com.challenge.get.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.challenge.get.model.Summary

@Entity(tableName = "summary")
class SummaryEntity(
    @PrimaryKey
    val id: Int,
    val started: String,
    val completed: String,
) {

    fun toModel() = Summary(
        id = id,
        started = started,
        completed = completed
    )


    companion object {

        fun fromModel(summary: Summary): SummaryEntity {
            return SummaryEntity(
                id = summary.id,
                started = summary.started,
                completed = summary.completed
            )
        }
    }
}