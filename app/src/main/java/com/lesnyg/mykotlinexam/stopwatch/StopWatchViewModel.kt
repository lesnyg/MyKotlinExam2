package com.lesnyg.mykotlinexam.stopwatch

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_stop_watch_main.*
import kotlinx.coroutines.NonCancellable
import java.util.*
import kotlin.concurrent.timer

class StopWatchViewModel:ViewModel(){
    val time : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val isRunning = MutableLiveData<Boolean>()
    init{
        time.value = 0
        isRunning.value = false
    }
    private var timerTask: Timer? = null
    private fun pause() {
        timerTask?.cancel()
    }

    private fun start() {
        timerTask = timer(period = 10) {
            //Background
            time.postValue(time.value?.plus(1))

        }
    }

    fun onStartButtonClicked(){
        isRunning.value = !isRunning.value!!
        if(isRunning.value!!){
            start()
        }else{
            pause()
        }
    }

    override fun onCleared() {
        pause()
        timerTask = null
        super.onCleared()
    }
}