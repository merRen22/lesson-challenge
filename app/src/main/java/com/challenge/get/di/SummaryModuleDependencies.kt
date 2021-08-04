package com.challenge.get.di

import com.challenge.get.base.ErrorHandler
import com.challenge.get.repository.SummaryRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface SummaryModuleDependencies {

    fun provideSummaryRepository(): SummaryRepository

    fun provideErrorHandler(): ErrorHandler
}