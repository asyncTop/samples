package cn.sh.cjvision.lib_common.di

import cn.sh.cjvision.lib_common.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Provides
    @Singleton
    fun providerRetrofit(url: String, logger: LoggingInterceptor, interceptor: Interceptor): Retrofit {
        val okHttpClient = OkHttpClient().newBuilder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            addInterceptor(interceptor)
            addInterceptor(logger)
        }
        return Retrofit.Builder().apply {
            baseUrl(url)
            client(okHttpClient.build())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Provides
    @Singleton
    fun providerLogger(): LoggingInterceptor {
        return LoggingInterceptor
            .Builder()
            .loggable(BuildConfig.DEBUG)
            .setLevel(Level.BASIC)
            .build()
    }

    @Provides
    @Singleton
    fun providerInterceptor(headers: HashMap<String, String>): Interceptor {
        return Interceptor {
            val request = it.request()
            val builder = request.newBuilder()
            for (key: String in headers.keys) {
                headers[key]?.let { value -> builder.addHeader(key, value) }
            }
            it.proceed(builder.build())
        }
    }
}