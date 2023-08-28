package com.project.currency.core.repositories

import com.project.currency.core.entities.Conversion
import com.project.currency.core.entities.ConversionHistory
import com.project.currency.core.entities.ExchangeRates
import com.project.currency.core.entities.Symbols
import com.project.currency.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {

    suspend fun getSymbols(): Flow<Resource<Symbols>>

    suspend fun convert(
        fromCurrency: String,
        toCurrency: String,
        amount: String
    ): Flow<Resource<Conversion>>

    suspend fun getConversionHistory(
        startDate: String,
        endDate: String
    ): Flow<Resource<ConversionHistory>>

    suspend fun getLatest(
        base: String,
    ): Flow<Resource<ExchangeRates>>
    suspend fun getLatest(): Flow<Resource<ExchangeRates>>
}