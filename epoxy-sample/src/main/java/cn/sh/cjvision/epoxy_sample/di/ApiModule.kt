package cn.sh.cjvision.epoxy_sample.di

import cn.sh.cjvision.epoxy_sample.Api
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
}