package cn.sh.cjvision.navigation_sample.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel2: ViewModel() {

    val input = MutableLiveData<String>()

    init {
        Log.d("UserViewModel",input.value?:"空")
    }
}