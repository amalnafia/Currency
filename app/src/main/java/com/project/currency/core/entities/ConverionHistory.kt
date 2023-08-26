package com.project.currency.core.entities

data class ConversionHistory(
    val success: Boolean,
    val timeSeries: Boolean,
    val startDate: String,
    val endDate: String,
    val base: String,
    val rates: Map<String, RateEntry>,
    val error: ErrorResponse? = null
)

data class RateEntry(
    val currencyRate: Map<String, Double>
)