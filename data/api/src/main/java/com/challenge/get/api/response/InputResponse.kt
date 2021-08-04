package com.challenge.get.api.response

import com.challenge.get.model.Input
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Response data for [Input]
 */
@Serializable
class InputResponse {

    @SerialName("startIndex")
    var startIndex: Int? = null

    @SerialName("endIndex")
    var endIndex: Int? = null

    fun toModel() = Input(
        startIndex = startIndex?: throw IllegalArgumentException("no startIndex specified"),
        endIndex = endIndex?: throw IllegalArgumentException("no endIndex specified"),
    )
}