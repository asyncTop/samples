package cn.sh.cjvision.epoxy_sample

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cn.sh.cjvision.epoxy_sample.databinding.ArticleEntity
import cn.sh.cjvision.lib_common.BaseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private var repository: MainRepository) : ViewModel() {

    private val _list = MutableStateFlow<BaseEntity<ArticleEntity>?>(null)

    val list:StateFlow<BaseEntity<ArticleEntity>?> = _list

//    private val _list = MutableLiveData<BaseEntity<ArticleEntity>>()
//
//    val list:LiveData<BaseEntity<ArticleEntity>> = _list

    init {
        getArticle()
    }

    fun getArticle(){
        viewModelScope.launch {
            repository.getArticles1()
                .catch {
                    Log.d("ERROE",it.message?:"")
                }
                .collect {
//                    _list.value = it
                    _list.value = it
                }
        }

    }
}