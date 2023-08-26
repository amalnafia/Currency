package com.project.currency.ui.screens.convertCurrency.views

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.compose.material.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun <T> AppDropDown(
    modifier: Modifier = Modifier,
    text: String = "",
    title: String = "",
    onValueChange: (String) -> Unit = {},
    onItemClicked: (item: T) -> Unit = {},
    dropDownList: List<T>,
    textItemComposable: @Composable() (item: T) -> Unit,
) {
    val shape = RoundedCornerShape(6.dp)
    val showMenu = remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        Column {
            Text(
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .padding(top = 10.dp),
                textAlign = TextAlign.Start,
                text = title,
                style = TextStyle(fontSize = 12.sp)
            )
            Box(
                modifier = Modifier
                    .clip(shape = shape)
                    .fillMaxWidth()
                    .height(55.dp)
                    .clip(shape = shape)
                    .clickable { showMenu.value = !showMenu.value },
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = text,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showMenu.value = true }
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                        .padding(16.dp)
                        .zIndex(1f)
                )
                DropdownMenu(
                    expanded = showMenu.value,
                    onDismissRequest = { showMenu.value = false }) {
                    dropDownList.forEach { dropDownItem ->
                        DropdownMenuItem(
                            onClick = {
                                onItemClicked(dropDownItem)
                                showMenu.value = !showMenu.value
                            }) {
                            textItemComposable(dropDownItem)
                        }
                    }
                }
            }

        }
    }
}