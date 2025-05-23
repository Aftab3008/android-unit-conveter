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
import androidx.compose.material3.MaterialTheme
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
import kotlin.math.roundToInt

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

    val inputList =
        listOf("Centimeters", "Meters", "Kilometers", "Inches", "Feet", "Yards", "Miles")
    val outputList =
        listOf("Centimeters", "Meters", "Kilometers", "Inches", "Feet", "Yards", "Miles")

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    val iConversionFactor = remember { mutableStateOf(1.0) }
    val oConversionFactor = remember { mutableStateOf(1.0) }

    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }


    fun convert() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val outputValueDouble =
            (inputValueDouble * iConversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        println(outputValueDouble.toString())
        outputValue = outputValueDouble.toString()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = inputValue,
            placeholder = { Text(text = "Enter a value") },
            onValueChange = {
                inputValue = it
                convert()
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Box {
                Button(onClick = {
                    iExpanded = !iExpanded
                }) {
                    Text(inputUnit)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Select an item")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {
                    iExpanded = false
                }) {
                    inputList.forEach {
                        DropdownMenuItem(text = { Text(it) }, onClick = {
                            inputUnit = it
                            iExpanded = false
                            iConversionFactor.value = when (it) {
                                "Centimeters" -> 0.01
                                "Meters" -> 1.0
                                "Kilometers" -> 1000.0
                                "Inches" -> 0.0254
                                "Feet" -> 0.3048
                                "Yards" -> 0.9144
                                "Miles" -> 1609.34
                                else -> 0.01
                            }
                            convert()
                        })
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = {
                    oExpanded = !oExpanded
                }) {
                    Text(outputUnit)
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Select an item")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {
                    oExpanded = false
                }) {
                    outputList.forEach {
                        DropdownMenuItem(text = { Text(it) }, onClick = {
                            outputUnit = it
                            oExpanded = false
                            oConversionFactor.value = when (it) {
                                "Centimeters" -> 0.01
                                "Meters" -> 1.0
                                "Kilometers" -> 1000.0
                                "Inches" -> 0.0254
                                "Feet" -> 0.3048
                                "Yards" -> 0.9144
                                "Miles" -> 1609.34
                                else -> 0.01
                            }
                            convert()
                        })
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue", style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme { UnitConverter() }
}
