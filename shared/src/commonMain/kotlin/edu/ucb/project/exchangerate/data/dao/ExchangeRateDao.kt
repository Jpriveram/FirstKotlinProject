package edu.ucb.project.exchangerate.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import edu.ucb.project.exchangerate.data.entity.ExchangeRateEntity

import kotlinx.coroutines.flow.Flow

@Dao
interface ExchangeRateDao {
    @Query("SELECT * FROM dollars")
    fun getList(): Flow<List<ExchangeRateEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dollar: ExchangeRateEntity)

    @Query("DELETE FROM dollars")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDollars(lists: List<ExchangeRateEntity>)
}