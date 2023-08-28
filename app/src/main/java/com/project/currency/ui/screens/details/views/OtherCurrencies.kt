package com.project.currency.ui.screens.details.views

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
import com.project.currency.core.entities.ExchangeRates


@Composable
fun OtherCurrencies(modifier: Modifier, base: String, latestRates: ExchangeRates) {
    Column(
        modifier.padding(16.dp)
    ) {
        Text(
            text = "Base Currency: ${base}",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(formatRates(latestRates.rates)) { rate ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    elevation = 4.dp
                ) {
                    Text(text = rate)
                }
            }
        }
    }
}

fun formatRates(rates: Map<String, Double>): List<String> {
    return rates.map { (currency, rate) ->
        "$currency: $rate"
    }.toList()
}