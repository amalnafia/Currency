package com.project.currency.data

import com.project.currency.core.repositories.CurrencyRepository
import com.project.currency.core.utils.Resource
import kotlinx.coroutines.flow.flow

class CurrencyRepositoryImpl(private val currencyService: CurrencyService) : CurrencyRepository {
    override suspend fun getSymbols() = flow {
        try {
            emit(Resource.loading())
            val response = currencyService.getSymbols(API_KEY)
            if (response.success)
                emit(Resource.success(response))
            else
                emit(Resource.error(response.error!!.info, null))
        } catch (e: Exception) {
            emit(Resource.error("No internet connection or server error", null))
        }
    }

    override suspend fun convert(
        fromCurrency: String,
        toCurrency: String,
        amount: String
    ) = flow {
        try {
            emit(Resource.loading())
            val response =
                currencyService.convert(API_KEY, fromCurrency, toCurrency, amount.toDouble())
            if (response.success)
                emit(Resource.success(response))
            else
                emit(Resource.error(response.error!!.info, null))
        } catch (e: Exception) {
            emit(Resource.error("No internet connection or server error", null))
        }
    }

    override suspend fun getConversionHistory(
        startDate: String,
        endDate: String,
    ) = flow {
        try {
            val response = currencyService.getConversionHistory(API_KEY, startDate, endDate)
            if (response.success) {
                emit(Resource.success(response))
            } else {
                emit(Resource.error(response.error?.info ?: "Unknown error", null))
            }
        } catch (e: Exception) {
            emit(Resource.error("No internet connection or server error", null))
        }
    }
    override suspend fun getLatest(
        base: String
    ) = flow {
        try {
            val response = currencyService.getLatest(API_KEY, base)
            if (response.success) {
                emit(Resource.success(response))
            } else {
                emit(Resource.error(response.error?.info ?: "Unknown error", null))
            }
        } catch (e: Exception) {
            emit(Resource.error("No internet connection or server error", null))
        }
    }
    override suspend fun getLatest() = flow {
        try {
            val response = currencyService.getLatest(API_KEY)
            if (response.success) {
                emit(Resource.success(response))
            } else {
                emit(Resource.error(response.error?.info ?: "Unknown error", null))
            }
        } catch (e: Exception) {
            emit(Resource.error("No internet connection or server error", null))
        }
    }

    companion object {
        const val API_KEY = "ca236833a7931b06b4c10da995cb4a02"
    }
}
