package com.challenge.get.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response data with [Lesson] list in it.
 */
@Serializable
class ListResponse {
    @SerialName("lessons")
    var items: List<LessonResponse>? = null
}