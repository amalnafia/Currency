package com.project.currency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.currency.ui.screens.details.ConversionHistoryScreen
import com.project.currency.ui.screens.convertCurrency.ConvertCurrencyScreen
import com.project.currency.ui.theme.CurrencyTheme
import com.project.currency.util.NavigationKeys.Arg.BASE_CURRENCY
import com.project.currency.util.NavigationKeys.Route.CONVERT_CURRENCY_SCREEN
import com.project.currency.util.NavigationKeys.Route.CONVERT_HISTORY_SCREEN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CurrencyApp()
                }
            }
        }
    }
}

@Composable
fun CurrencyApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CONVERT_CURRENCY_SCREEN
    ) {
        composable(route = CONVERT_CURRENCY_SCREEN) {
            ConvertCurrencyScreen(onDetailsClicked = { baseCurrency ->
                navController.navigate(
                    "${CONVERT_CURRENCY_SCREEN}/${baseCurrency}"
                )
            })
        }
        composable(
            route = CONVERT_HISTORY_SCREEN, arguments = listOf(
                navArgument(BASE_CURRENCY) { type = NavType.StringType })
        ) {
            ConversionHistoryScreen(navBackStack = it)
        }
    }
}