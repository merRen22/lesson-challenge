package com.challenge.get.database

import com.challenge.get.model.Summary

/**
 * [Summary] DB
 */
interface SummaryDb {
    /**
     * get all [Summary]
     */
    suspend fun getAll(): List<Summary>


    /**
     * insert [Summary]
     */
    suspend fun insert(summary: Summary)
}