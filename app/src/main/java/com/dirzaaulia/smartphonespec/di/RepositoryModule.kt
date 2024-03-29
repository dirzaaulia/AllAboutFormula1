package com.dirzaaulia.smartphonespec.di

import com.dirzaaulia.smartphonespec.network.Service
import com.dirzaaulia.smartphonespec.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideRepository(service: Service): Repository {
        return Repository(service)
    }
}