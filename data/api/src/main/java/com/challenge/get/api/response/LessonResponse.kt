package com.challenge.get.api.response

import com.challenge.get.model.Lesson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response data for [Lesson]
 */
@Serializable
class LessonResponse {

    @SerialName("id")
    var id: Int? = null

    @SerialName("content")
    var content: List<ContentReponse>? = null

    @SerialName("input")
    var input: InputResponse? = null

    fun toModel() = Lesson(
        id = id ?: 0,
        content = content?.let { steps ->
            steps.map { step -> step.toModel() }
        } ?: throw IllegalArgumentException("no steps for lesson were received"),
        input = input?.toModel(),
    )
}