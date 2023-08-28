package com.project.currency.data

import com.project.currency.core.entities.Conversion
import com.project.currency.core.entities.ConversionHistory
import com.project.currency.core.entities.ExchangeRates
import com.project.currency.core.entities.Symbols
import retrofit2.http.*

interface CurrencyService {

    @GET("symbols")
    suspend fun getSymbols(@Query("access_key") apiKey: String): Symbols

    @POST("convert")
    suspend fun convert(
        @Query("access_key") apiKey: String,
        @Query("from") fromCurrency: String,
        @Query("to") toCurrency: String,
        @Query("amount") amount: Double
    ): Conversion

    @GET("timeseries")
    suspend fun getConversionHistory(
        @Query("access_key") apiKey: String,
        @Query("start_date") stareDate: String,
        @Query("end_date") endDate: String,
    ): ConversionHistory

    @GET("latest")
    suspend fun getLatest(
        @Query("access_key") apiKey: String,
        @Query("base") base: String,
    ): ExchangeRates

    @GET("latest")
    suspend fun getLatest(
        @Query("access_key") apiKey: String,
    ): ExchangeRates
}