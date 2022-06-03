package com.liontimer.application.pro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class LocalTrack(
    val time: String?,
    @PrimaryKey val id: Int? = null
)