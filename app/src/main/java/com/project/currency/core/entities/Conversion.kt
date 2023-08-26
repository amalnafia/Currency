package com.project.currency.core.entities

data class Conversion(
    val success: Boolean,
    val query: Query,
    val info: Info,
    val historical: String,
    val date: String,
    val result: Double,
    val error: ErrorResponse? = null
)

data class Query(
    val from: String,
    val to: String,
    val amount: Double
)

data class Info(
    val timestamp: Long,
    val rate: Double
)
