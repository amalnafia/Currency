package com.project.currency.ui.screens.convertCurrency

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.currency.core.utils.Resource
import com.project.currency.core.utils.Status
import com.project.currency.ui.screens.convertCurrency.views.AppDropDown
import com.project.currency.ui.screens.convertCurrency.views.MessageDialogView

@Composable
fun ConvertCurrencyScreen(
    viewModel: ConvertCurrencyViewModel = hiltViewModel(),
    onDetailsClicked: (String) -> Unit
) {
    val symbolsState = viewModel.symbolsState
    when {
        symbolsState.isLoading() -> CircularProgressIndicator()
        symbolsState.isError() -> Text(
            symbolsState.message.toString(),
            Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )

        symbolsState.isSuccess() -> {
            symbolsState.data?.symbols?.let {
                ConvertCurrencyBody(
                    symbols = viewModel.parseCurrencySymbols(it),
                    viewModel = viewModel,
                    onDetailsClicked = onDetailsClicked
                )
            }
        }
    }
}

@Composable
fun ConvertCurrencyBody(
    symbols: List<String>,
    viewModel: ConvertCurrencyViewModel,
    onDetailsClicked: (String) -> Unit
) {

    val showDialog = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Convert Currency", style = MaterialTheme.typography.h5)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppDropDown(
                modifier = Modifier.weight(1f),
                text = viewModel.fromCurrency,
                dropDownList = symbols,
                title = "From Currency",
                onValueChange = { viewModel.fromCurrency = it },
                onItemClicked = { clickedItem ->
                    viewModel.fromCurrency = clickedItem
                },
                textItemComposable = { dropDownItem ->
                    Text(text = dropDownItem)
                },
            )

            // Swap Button
            Button(
                onClick = {
                    if (viewModel.fromCurrency != "from" && viewModel.toCurrency != "to") {
                        val temp = viewModel.fromCurrency
                        viewModel.fromCurrency = viewModel.toCurrency
                        viewModel.toCurrency = temp
                        viewModel.convert()
                    } else {
                        errorMessage.value = "Please Select from and to First"
                        showDialog.value = true
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 20.dp)
            ) {
                Text("Swap")
            }

            // To Currency Dropdown
            AppDropDown(
                modifier = Modifier.weight(1f),
                text = viewModel.toCurrency,
                dropDownList = symbols,
                title = "To Currency",
                onValueChange = { viewModel.toCurrency = it },
                onItemClicked = { clickedItem ->
                    viewModel.toCurrency = clickedItem
                },
                textItemComposable = { dropDownItem ->
                    Text(text = dropDownItem)
                },
            )

        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(
                value = viewModel.amount,
                onValueChange = {
                    viewModel.amount = it
                    if (viewModel.fromCurrency != "from" && viewModel.toCurrency != "to")
                        viewModel.convert()
                },
                label = { Text("Amount") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))

            OutlinedTextField(
                value = viewModel.convertedAmount,
                onValueChange = {},
                label = { Text("Converted Amount") },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onDetailsClicked(viewModel.fromCurrency) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Details")
        }
    }

    if (viewModel.convertState.isError()) {
        errorMessage.value = viewModel.convertState.message ?: ""
        showDialog.value = true
        viewModel.convertState = Resource(Status.IDLE)
    }

    if (showDialog.value)
        MessageDialogView(
            showDialog = showDialog,
            title = "Error",
            body = errorMessage.value,
            onDismissClicked = {}
        )
}
