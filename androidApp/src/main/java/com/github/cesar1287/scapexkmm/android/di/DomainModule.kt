package com.github.cesar1287.scapexkmm.android.di

import com.github.cesar1287.scapexkmm.domain.LaunchesUseCase
import com.github.cesar1287.scapexkmm.domain.LaunchesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideLaunchesUseCase(): LaunchesUseCase {
        return LaunchesUseCaseImpl()
    }
}