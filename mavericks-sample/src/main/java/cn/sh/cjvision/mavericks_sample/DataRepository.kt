package cn.sh.cjvision.mavericks_sample

import cn.sh.cjvision.mavericks_sample.net.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class DataRepository @Inject constructor(private val api: Api) {
//    suspend fun getHotKey() = api.getHotKey()

    suspend fun getHotKey() = flow {
        emit(api.getHotKey())
    }.flowOn(Dispatchers.IO)
}