package com.project.currency.util

import com.project.currency.util.NavigationKeys.Arg.BASE_CURRENCY


object NavigationKeys {
    object Arg {
        const val BASE_CURRENCY = "base_currency"
    }

    object Route {
        const val CONVERT_CURRENCY_SCREEN = "convert_currency_screen"
        const val CONVERT_HISTORY_SCREEN = "$CONVERT_CURRENCY_SCREEN/{$BASE_CURRENCY}"
    }
}