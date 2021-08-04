package com.challenge.get.base.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

var format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'")

val tFormatParserMiliseconds: SimpleDateFormat by lazy {
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    simpleDateFormat
}

fun parse(parser: SimpleDateFormat, dateToParse: String): Date {
    return try {
        parser.parse(dateToParse)
    } catch (e: ParseException) {
        parse(tFormatParserMiliseconds, dateToParse)
    }
}