package com.challenge.get.api.response

import com.challenge.get.model.Lesson
import com.challenge.get.model.Step
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response data for [Content]
 */
@Serializable
class ContentReponse {

    @SerialName("color")
    var color: String? = null

    @SerialName("text")
    var text: String? = null

    fun toModel() = Step(
        color = color?: throw IllegalArgumentException("no color specified"),
        text = text?: throw IllegalArgumentException("no text specified"),
    )
}