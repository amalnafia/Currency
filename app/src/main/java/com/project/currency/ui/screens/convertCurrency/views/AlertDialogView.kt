package com.project.currency.ui.screens.convertCurrency.views


import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

@Composable
fun MessageDialogView(
    title: String = "Error",
    body: String,
    onDismissClicked: () -> Unit = {},
    showDialog: MutableState<Boolean>,) {
    if (showDialog.value)
        AlertDialogView(title = title, body = body, onDismissClicked = {
            showDialog.value = false
            onDismissClicked()
        })
}

@Composable
fun AlertDialogView(title: String, body: String, onDismissClicked: () -> Unit) {
    AlertDialog(
            title = {
                Text(title)
            },
            text = {
                Text(body)
            },
            onDismissRequest = {
                onDismissClicked()
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDismissClicked()
                    },
                ) {
                    Text("Ok", color = Color.White)
                }
            },
            dismissButton = { }
    )
}