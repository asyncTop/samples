package cn.sh.cjvision.epoxy_sample.di

import cn.sh.cjvision.epoxy_sample.maverick.MaverickViewModel
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MaverickViewModel::class)
    fun exampleViewModelFactory(factory: MaverickViewModel.Factory): AssistedViewModelFactory<*, *>
}