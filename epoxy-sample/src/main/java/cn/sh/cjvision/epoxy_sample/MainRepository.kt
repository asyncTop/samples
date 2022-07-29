package cn.sh.cjvision.epoxy_sample

import cn.sh.cjvision.epoxy_sample.net.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: Api) {
    suspend fun getArticles1() = flow {
        emit(api.getArticle())
    }.flowOn(Dispatchers.IO)

    suspend fun getArticles() = api.getArticle()

    suspend fun getHotKey() = flow {
        emit(api.getHotKey())
    }.flowOn(Dispatchers.IO)
}