package com.challenge.get.repository

import com.challenge.get.api.LessonApi
import com.challenge.get.model.Lesson
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for Lesson
 */
@Singleton
class LessonRepository @Inject constructor(
    private val lessonApi: LessonApi
) {

    /**
     * Makes a request to the api to get the lessons
     */
    suspend fun getLessons(): List<Lesson> {
        return lessonApi.getLessons()
    }

}