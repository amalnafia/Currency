package com.project.currency.core.entities

data class ExchangeRates(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
    val error: ErrorResponse? = null
)

