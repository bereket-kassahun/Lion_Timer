package com.liontimer.application.pro.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(track: LocalTrack)

    @Delete
    suspend fun deleteLog(track: LocalTrack)

    @Query("DELETE FROM tracks")
    suspend fun nukeTable()

    @Query("SELECT * FROM tracks WHERE id = :id")
    suspend fun getLogById(id: Int): LocalTrack?

    @Query("SELECT * FROM tracks")
    fun getLogs(): Flow<List<LocalTrack>>

    @Query("SELECT * FROM tracks ORDER BY id DESC")
    fun getLogsDesc(): Flow<List<LocalTrack>>
}