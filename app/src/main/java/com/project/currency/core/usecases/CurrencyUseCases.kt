package com.project.currency.core.usecases

import com.project.currency.core.repositories.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

object CurrencyUseCases

suspend fun CurrencyUseCases.getSymbols(
    currencyRepository: CurrencyRepository
) = currencyRepository.getSymbols()
    .flowOn(Dispatchers.IO)

suspend fun CurrencyUseCases.convert(
    currencyRepository: CurrencyRepository,
    fromCurrency: String,
    toCurrency: String,
    amount: String
) = currencyRepository.convert(
    fromCurrency = fromCurrency,
    toCurrency = toCurrency,
    amount = amount
).flowOn(Dispatchers.IO)

suspend fun CurrencyUseCases.getConversionHistory(
    currencyRepository: CurrencyRepository,
    startDate: String,
    endDate: String
) = currencyRepository.getConversionHistory(
    startDate = startDate,
    endDate = endDate
).flowOn(Dispatchers.IO)

suspend fun CurrencyUseCases.getLatest(
    currencyRepository: CurrencyRepository,
    base: String
) = currencyRepository.getLatest(
    base = base
).flowOn(Dispatchers.IO)

suspend fun CurrencyUseCases.getLatest(
    currencyRepository: CurrencyRepository
) = currencyRepository.getLatest().flowOn(Dispatchers.IO)