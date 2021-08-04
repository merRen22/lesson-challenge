package com.challenge.get.api

import com.challenge.get.model.Lesson

/**
 * Lesson API
 */
interface LessonApi {

    /**
     * get [Lesson] list from remote.
     */
    suspend fun getLessons(): List<Lesson>
}