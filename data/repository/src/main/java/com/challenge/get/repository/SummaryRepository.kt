package com.challenge.get.repository

import com.challenge.get.database.SummaryDb
import com.challenge.get.model.Summary
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for [Summary]
 */
@Singleton
class SummaryRepository @Inject constructor(
    private val summaryDb: SummaryDb
) {


    /**
     * Makes a request to the local DB to get the summaries
     */
    suspend fun findAllSummaries(): List<Summary> {
        return summaryDb.getAll()
    }

    /**
     * Inserts a summary to the local db
     */
    suspend fun addSummary(summary: Summary) {
        return summaryDb.insert(summary)
    }
}