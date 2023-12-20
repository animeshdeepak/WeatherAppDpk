package com.example.weatherappdpk.di

import com.example.weatherappdpk.data.repoimpl.HomeRepoImpl
import com.example.weatherappdpk.domain.repo.HomeRepo
import com.example.weatherappdpk.helper.MviActionProcessor
import com.example.weatherappdpk.presentation.HomeAction
import com.example.weatherappdpk.presentation.HomeActionProcessor
import com.example.weatherappdpk.presentation.HomeResult
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindHomeActionProcessor(
        processor: HomeActionProcessor
    ): MviActionProcessor<HomeAction, HomeResult>

    @Binds
    @Singleton
    abstract fun bindHomeRepo(
        repoImpl: HomeRepoImpl
    ): HomeRepo

}