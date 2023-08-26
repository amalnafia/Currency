package com.project.currency.di

import com.project.currency.core.repositories.CurrencyRepository
import com.project.currency.data.CurrencyRepositoryImpl
import com.project.currency.data.CurrencyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun providesCurrencyRepository(currencyService: CurrencyService): CurrencyRepository {
        return CurrencyRepositoryImpl(currencyService)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): CurrencyService {
        return retrofit.create(CurrencyService::class.java)
    }
}