package com.project.currency.ui.screens.conversionHistory.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.currency.core.entities.ConversionHistory


@Composable
fun HistoricalList(modifier: Modifier, conversionHistory: ConversionHistory) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        Text(
            text = "Historical data for last 3 days",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(getDatesFromConversionHistory(conversionHistory)) { date ->
                ConversionHistoryListItem(date, conversionHistory)
            }
        }

    }
}

@Composable
fun ConversionHistoryListItem(
    date: String,
    conversionHistory: ConversionHistory
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Date: ${date}",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Column {
                formatCurrenciesRateForDate(conversionHistory, date)!!.forEach { currencyAndRate ->
                    Text(
                        text = currencyAndRate,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}

fun getDatesFromConversionHistory(historicalConversions: ConversionHistory): List<String> {
    return historicalConversions.rates.keys.toList()
}


fun formatCurrenciesRateForDate(
    historicalConversions: ConversionHistory,
    date: String
): List<String>? {
    return historicalConversions.rates[date]?.currencyRate?.map { (currency, rate) ->
        "$currency: $rate"
    }?.toList()
}