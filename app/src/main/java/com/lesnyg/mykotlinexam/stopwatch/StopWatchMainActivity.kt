package com.lesnyg.mykotlinexam.stopwatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.lesnyg.mykotlinexam.R
import kotlinx.android.synthetic.main.activity_stop_watch_main.*

class StopWatchMainActivity : AppCompatActivity() {
    //초기화  val viewModel = ViewModelProviders.of(this).get(StopWatchViewModel::class.java)대신 이렇게도 가능
//    val viewModel:StopWatchViewModel by lazy {
//        ViewModelProviders.of(this).get(StopWatchViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stop_watch_main)

        val viewModel = ViewModelProviders.of(this).get(StopWatchViewModel::class.java)
        viewModel.time.observe(this,androidx.lifecycle.Observer {time ->
            val sec = time / 100
            val milli = time % 100
            sec_text.text = "$sec"
            milli_text.text = "$milli"
        })

        viewModel.isRunning.observe(this,androidx.lifecycle.Observer {isRunning ->
            if(isRunning){
                start_btn.text = "Pause"
            }else{
                start_btn.text = "Start"
            }
        })
        start_btn.setOnClickListener {
            viewModel.onStartButtonClicked()

        }
    }
}
