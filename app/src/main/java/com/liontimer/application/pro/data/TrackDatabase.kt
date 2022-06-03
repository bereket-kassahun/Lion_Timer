package com.liontimer.application.pro.data


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalTrack::class], version = 1)
abstract class TrackDatabase: RoomDatabase() {
    abstract val dao: TrackDao
}