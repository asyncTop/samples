package cn.sh.cjvision.epoxy_sample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DataRepository @Inject constructor(private val api: Api) {
    suspend fun articleTop() = flow {
        emit(api.articleTop())
    }.flowOn(Dispatchers.IO)
}