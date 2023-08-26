package com.project.currency.core.entities

data class Symbols(
    val success: Boolean,
    val symbols: Map<String, String>,
    val error: ErrorResponse? = null
)

data class ErrorResponse(
    val code: Int,
    val type: String,
    val info: String
)
