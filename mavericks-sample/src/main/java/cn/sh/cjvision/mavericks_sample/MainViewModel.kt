package cn.sh.cjvision.mavericks_sample

import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import kotlinx.coroutines.launch

class MainViewModel @AssistedInject constructor(
    @Assisted initState: MainState,
    var repository: DataRepository
) : MavericksViewModel<MainState>(initState) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<MainViewModel, MainState> {
        override fun create(state: MainState): MainViewModel
    }

    companion object :
        MavericksViewModelFactory<MainViewModel, MainState> by hiltMavericksViewModelFactory()

    init {
        getHotKeys()
    }


    fun getHotKeys() = withState {
        if (it.request is Loading) return@withState
        viewModelScope.launch {
            repository.getHotKey()
                .execute(Dispatchers.IO, retainValue = MainState::request) { state ->
                    copy(request = state, hotKeys = state()?.data ?: emptyList())
                }
        }
//        suspend {
//            repository.getHotKey()
//        }.execute(Dispatchers.IO, retainValue = MainState::request) { state ->
//            copy(request = state, hotKeys = state()?.data ?: emptyList())
//        }
    }
}