package cn.sh.cjvision.epoxy_sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.sh.cjvision.epoxy_sample.entity.ArticleTop
import cn.sh.cjvision.lib_common.BaseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpoxyViewModel @Inject constructor(var repository: DataRepository) : ViewModel() {


    private var _articleTop = MutableStateFlow<BaseEntity<List<ArticleTop>>?>(null)

    var articleTop: StateFlow<BaseEntity<List<ArticleTop>>?> = _articleTop

    fun articleTop() {
        viewModelScope.launch {
            repository.articleTop()
                .onEach {
                    _articleTop.value = it
                }.launchIn(viewModelScope)
        }
    }
}