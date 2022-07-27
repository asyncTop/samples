package cn.sh.cjvision.mavericks_sample.di

import cn.sh.cjvision.mavericks_sample.net.Api
import cn.sh.cjvision.mavericks_sample.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun providerApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }


    @Provides
    @Singleton
    fun providerBaseUrl(): String {
        return if (BuildConfig.DEBUG) "https://www.wanandroid.com/" else "https://www.wanandroid.com/"
    }

    @Provides
    @Singleton
    fun providerHeader(): HashMap<String, String> {
        return HashMap()
    }
}