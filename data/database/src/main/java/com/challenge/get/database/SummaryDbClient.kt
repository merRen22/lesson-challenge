package com.challenge.get.database

import com.challenge.get.database.dao.SummaryDao
import com.challenge.get.database.entity.SummaryEntity
import com.challenge.get.model.Summary
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class SummaryDbClient(private val summaryDao: SummaryDao) : SummaryDb {
    override suspend fun getAll(): List<Summary> =
        summaryDao.getAll().map { summary -> summary.toModel() }

    override suspend fun insert(summary: Summary) {
        withContext(IO) {
            summaryDao.insert(SummaryEntity.fromModel(summary))
        }
    }
}