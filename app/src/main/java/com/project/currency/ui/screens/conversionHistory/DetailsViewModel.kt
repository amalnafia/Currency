package com.project.currency.ui.screens.conversionHistory

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.currency.core.entities.ConversionHistory
import com.project.currency.core.entities.ExchangeRates
import com.project.currency.core.repositories.CurrencyRepository
import com.project.currency.core.usecases.CurrencyUseCases
import com.project.currency.core.usecases.getConversionHistory
import com.project.currency.core.usecases.getLatest
import com.project.currency.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val currencyRepository: CurrencyRepository) :
    ViewModel() {
    var conversionHistoryState by mutableStateOf<Resource<ConversionHistory>>(Resource.loading())
    var latestState by mutableStateOf<Resource<ExchangeRates>>(Resource.loading())
    private val calendar = Calendar.getInstance()
    private var startDate = SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
    private val endDate = SimpleDateFormat("yyyy-MM-dd").format(calendar.time)

    init {
        calendar.add(Calendar.DAY_OF_MONTH, -3)
        startDate = SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
        getConversionHistory()
    }

    private fun getConversionHistory() {
        viewModelScope.launch {
            CurrencyUseCases.getConversionHistory(currencyRepository, startDate, endDate)
                .collect {
                    conversionHistoryState = it
                }
        }
    }

    fun getLatest(baseCurrency: String) {
        viewModelScope.launch {
            CurrencyUseCases.getLatest(currencyRepository, baseCurrency)
                .collect {
                    latestState = it
                }
        }
    }
}