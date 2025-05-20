package com.learning.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.learning.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UnitConverter(modifier: Modifier = Modifier) {
    var expanded1 by remember { mutableStateOf(false) }
    var expandedValue1 by remember { mutableStateOf("") }

    var expanded2 by remember { mutableStateOf(false) }
    var expandedValue2 by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter")
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = "$expandedValue1 $expandedValue2",
            placeholder = { Text(text = "Enter a value") },
            onValueChange = {}
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Box {
                Button(onClick = {
                    println("clicked")
                    expanded1 = !expanded1
                }) {
                    Text("Select")
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Select an item")
                }
                DropdownMenu(expanded = expanded1, onDismissRequest = {}) {
                    DropdownMenuItem(text = { Text("Item 1") }, onClick = {
                        expandedValue1 = "Item 1"
                        expanded1 = false
                    })
                    DropdownMenuItem(text = { Text("Item 2") }, onClick = {
                        expandedValue1 = "Item 2"
                        expanded1 = false
                    })
                    DropdownMenuItem(text = { Text("Item 3") }, onClick = {
                        expandedValue1 = "Item 3"
                        expanded1 = false
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = {
                    expanded2 = !expanded2
                }) {
                    Text("Select")
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Select an item")
                }
                DropdownMenu(expanded = expanded2, onDismissRequest = {}) {
                    DropdownMenuItem(text = { Text("Item 1") }, onClick = {
                        expandedValue2 = "Item 1"
                        expanded2 = false
                    })
                    DropdownMenuItem(text = { Text("Item 2") }, onClick = {
                        expandedValue2 = "Item 2"
                        expanded2 = false
                    })
                    DropdownMenuItem(text = { Text("Item 3") }, onClick = {
                        expandedValue2 = "Item 3"
                        expanded2 = false
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: TODO")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme { UnitConverter() }
}
