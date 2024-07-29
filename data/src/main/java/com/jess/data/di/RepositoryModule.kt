package com.jess.data.di

import com.jess.data.repository.RemoteDataRepository
import com.jess.data.repository.RemoteDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRemoteDataRepository(remoteDataRepository: RemoteDataRepositoryImpl): RemoteDataRepository
}