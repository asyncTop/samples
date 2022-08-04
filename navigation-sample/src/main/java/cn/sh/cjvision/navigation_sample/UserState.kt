package cn.sh.cjvision.navigation_sample

import cn.sh.cjvision.lib_common.BaseEntity
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class UserState(
    val hotKeys: List<String> = emptyList(),
    val request: Async<BaseEntity<List<String>>> = Uninitialized
) : MavericksState