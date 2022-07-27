package cn.sh.cjvision.mavericks_sample

import cn.sh.cjvision.lib_common.BaseEntity
import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.Uninitialized

data class MainState(
    val hotKeys: List<HotKeyEntity> = emptyList(),
    val request: Async<BaseEntity<List<HotKeyEntity>>> = Uninitialized
) : MavericksState