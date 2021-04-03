package com.taymiyyah.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.taymiyyah.UDFBirds.onError
import com.taymiyyah.UDFBirds.onIdle
import com.taymiyyah.UDFBirds.onLoading
import com.taymiyyah.UDFBirds.onSuccess

class MainActivity : AppCompatActivity() {
    lateinit var myViewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        observe()
    }

    private fun observe() {
        myViewModel.myDataState().observe(this) {
            it.onLoading { Log.d("myDataState", "onLoading: ") }
            it.onSuccess {
                Log.d("myDataState", "onSuccess:$data") }
            it.onError { Log.d("myDataState", "onError:$message ") }
            it.onIdle { Log.d("myDataState", "onIdle: ") }
        }
    }

}

