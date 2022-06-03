package com.liontimer.application.pro.domain

import com.liontimer.application.pro.data.LocalTrack
import kotlinx.coroutines.flow.Flow


interface TrackRepository {
    suspend fun insertTrack(logg: LocalTrack)

    suspend fun deleteTrack(logg: LocalTrack)

    suspend fun getTrackById(id: Int): LocalTrack?

    fun getTracks(): Flow<List<LocalTrack>>

    fun getTracksDesc() : Flow<List<LocalTrack>>

    suspend fun nukeTable()
}