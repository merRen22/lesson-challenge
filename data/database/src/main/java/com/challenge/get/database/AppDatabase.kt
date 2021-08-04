package com.challenge.get.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.get.database.dao.SummaryDao
import com.challenge.get.database.entity.SummaryEntity

/**
 * App Database
 */
@Database(entities = [SummaryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val repositoryDao: SummaryDao
}