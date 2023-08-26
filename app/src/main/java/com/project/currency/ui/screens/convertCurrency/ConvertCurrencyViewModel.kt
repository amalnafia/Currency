package com.project.currency.ui.screens.convertCurrency

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.currency.core.entities.Conversion
import com.project.currency.core.entities.Symbols
import com.project.currency.core.repositories.CurrencyRepository
import com.project.currency.core.usecases.CurrencyUseCases
import com.project.currency.core.usecases.convert
import com.project.currency.core.usecases.getSymbols
import com.project.currency.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConvertCurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {
    var fromCurrency by mutableStateOf("from")
    var toCurrency by mutableStateOf("to")
    var amount by mutableStateOf("1")
    var convertedAmount by mutableStateOf("0.0")

    var symbolsState by mutableStateOf<Resource<Symbols>>(Resource.loading())
    var convertState by mutableStateOf<Resource<Conversion>>(Resource.loading())

    init {
        getSymbols()
    }

    private fun getSymbols() {
        viewModelScope.launch {
            CurrencyUseCases.getSymbols(currencyRepository)
                .collect { symbolsState = it }
        }
    }

    fun convert() {
        viewModelScope.launch {
            CurrencyUseCases.convert(currencyRepository, fromCurrency, toCurrency, amount)
                .collect {
                    convertState = it
                    if (convertState.isSuccess())
                        convertedAmount = it.data?.result.toString()
                }
        }
    }

    fun parseCurrencySymbols(symbolsMap: Map<String, String>): List<String> {
        return symbolsMap.keys.toList()
    }
}