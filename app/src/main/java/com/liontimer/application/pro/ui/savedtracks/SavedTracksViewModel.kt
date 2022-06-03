package com.liontimer.application.pro.ui.savedtracks

import android.media.MediaParser
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liontimer.application.pro.data.TrackDatabase
import com.liontimer.application.pro.data.TrackRepositoryImpl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SavedTracksViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val tracks = MutableLiveData<List<String>>()

    lateinit var repository: TrackRepositoryImpl

    fun setRepository(database: TrackDatabase){
        repository = TrackRepositoryImpl(database.dao)
    }

    fun clearAll(){
        viewModelScope.launch {
            repository.nukeTable()
        }
    }

    fun getAllTracks(){
        viewModelScope.launch {
            repository.getTracks().collect { tracksList ->
                tracks.value = tracksList.map {
                    it.time ?: ""
                }
            }
        }
    }
}