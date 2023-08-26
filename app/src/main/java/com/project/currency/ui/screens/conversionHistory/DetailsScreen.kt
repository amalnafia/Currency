package com.project.currency.ui.screens.conversionHistory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.project.currency.core.entities.ConversionHistory
import com.project.currency.core.entities.ExchangeRates
import com.project.currency.core.entities.RateEntry
import com.project.currency.ui.screens.conversionHistory.views.HistoricalList
import com.project.currency.ui.screens.conversionHistory.views.OtherCurrencies
import com.project.currency.util.NavigationKeys.Arg.BASE_CURRENCY

@Composable
fun ConversionHistoryScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    navBackStack: NavBackStackEntry
) {
    val baseCurrency = navBackStack.arguments?.getString(BASE_CURRENCY)
    viewModel.getLatest(baseCurrency ?: "")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val conversionHistoryState = viewModel.conversionHistoryState
        when {
            conversionHistoryState.isLoading() -> CircularProgressIndicator()
            conversionHistoryState.isError() -> Text(
                conversionHistoryState.message.toString(),
                Modifier.fillMaxSize()
            )

            conversionHistoryState.isSuccess() -> {
                conversionHistoryState.data?.let { conversionHistory ->
                    HistoricalList(
                        modifier = Modifier.weight(1f),
                        conversionHistory = conversionHistory
                    )
                }
            }
        }
        val latestState = viewModel.latestState
        when {
            latestState.isLoading() -> CircularProgressIndicator()
            latestState.isError() -> Text(latestState.message.toString(), Modifier.fillMaxSize())
            latestState.isSuccess() -> {
                latestState.data?.let { latestRates ->
                    OtherCurrencies(
                        modifier = Modifier.weight(1f),
                        base = baseCurrency ?: "USD",
                        latestRates = latestRates
                    )
                }
            }
        }
    }
}

//dummy data

//val sampleRateEntry = RateEntry(
//    currencyRate = mapOf(
//        "USD" to 1.322891,
//        "AUD" to 1.278047,
//        "CAD" to 1.302303
//    )
//)
//
//val conversionHistory = ConversionHistory(
//    success = true,
//    timeSeries = true,
//    startDate = "2023-08-01",
//    endDate = "2023-08-03",
//    base = "EUR",
//    rates = mapOf(
//        "2012-05-01" to sampleRateEntry,
//        "2012-05-02" to sampleRateEntry,
//        "2012-05-03" to sampleRateEntry
//    ),
//)
//
//val ratesMap = mapOf(
//    "GBP" to 0.72007,
//    "JPY" to 107.346001,
//    "EUR" to 0.813399,
//    "AAM" to 0.813399,
//    "DD" to 0.8136599,
//    "DFR" to 0.8139,
//    "WWR" to 0.8133989,
//    "VVR" to 0.8133599,
//    "OOR" to 0.8133799,
//)
//
//val latestRates = ExchangeRates(
//    success = true,
//    timestamp = 1519296206,
//    base = "EUR",
//    date = "2023-08-26",
//    rates = ratesMap,
//)