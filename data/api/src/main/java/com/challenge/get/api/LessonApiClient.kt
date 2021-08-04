package com.challenge.get.api

import com.challenge.get.api.response.ListResponse
import com.challenge.get.model.Lesson
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET

/**
 * Lesson API client
 */
class LessonApiClient(retrofit: Retrofit) : LessonApi {

    interface Service {

        @GET("lessons")
        suspend fun getLessons(
        ): ListResponse
    }

    private val service = retrofit.create(Service::class.java)

    override suspend fun getLessons(): List<Lesson> {
        return withContext(IO) {
            service.getLessons().items?.let {
                it.map { item ->
                    item.toModel()
                }
            } ?: emptyList()
        }
    }
}