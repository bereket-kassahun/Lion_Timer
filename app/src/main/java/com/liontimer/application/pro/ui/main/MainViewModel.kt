package com.liontimer.application.pro.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liontimer.application.pro.data.LocalTrack
import com.liontimer.application.pro.data.TrackDatabase
import com.liontimer.application.pro.data.TrackRepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel : ViewModel() {

    val displayTime = MutableLiveData<String>("00:00:00")
    var time = 0
    var start = MutableLiveData<Boolean>(false)
    val firstTime = MutableLiveData<Boolean>(true)

    lateinit var repository: TrackRepositoryImpl

    fun setRepository(database: TrackDatabase){
        repository = TrackRepositoryImpl(database.dao)
    }

    fun saveTrack(){
        val currentTime = displayTime.value
        viewModelScope.launch {
            repository.insertTrack(LocalTrack(currentTime))
        }
    }


    fun reset(){
        time = 0
        displayTime.value = String.format(Locale.getDefault(), "%d:%02d:%02d", 0, 0, 0);
    }

    fun start(){
        if(start.value == false){
            reset()
            start.value = true
            viewModelScope.launch {
                stopWatch()
            }
        }else{
            reset()
        }
    }

    fun pauseResume(){
        if(start.value == false){
            start.value = true
            viewModelScope.launch {
                stopWatch()
            }
        }else{
            start.value = false
        }
    }

    suspend fun stopWatch(){
        while (start.value == true){
            time += 1
            displayTime.value = String.format(Locale.getDefault(), "%02d:%02d:%02d", ((time/100) % 3600)/60, (time/100)%60, (time%99)*6/10);
            delay(10)
        }
    }

}