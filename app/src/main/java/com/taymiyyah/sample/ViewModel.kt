package com.taymiyyah.sample

import androidx.lifecycle.ViewModel
import com.taymiyyah.UDFBirds.performNetworkCallOperation
import com.taymiyyah.sample.getDateApi

class MyViewModel  : ViewModel() {

    fun myDataState () = performNetworkCallOperation {
        getDateApi().convertFromGDateTOH("8/2016")
    }
}