package com.challenge.get.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.get.database.entity.SummaryEntity

/**
 * [Summary] Dao
 */
@Dao
abstract class SummaryDao {

    @Query("SELECT * FROM summary")
    abstract suspend fun getAll(): List<SummaryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(summaryEntity: SummaryEntity)

}