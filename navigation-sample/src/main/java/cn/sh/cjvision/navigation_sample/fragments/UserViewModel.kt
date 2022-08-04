package cn.sh.cjvision.navigation_sample.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import cn.sh.cjvision.navigation_sample.UserState
import com.airbnb.mvrx.MavericksViewModel

class UserViewModel(initState: UserState) :
    MavericksViewModel<UserState>(initialState = initState) {

    val input = MutableLiveData<String>()

    init {
        Log.d("UserViewModel",input.value?:"空")
    }
}