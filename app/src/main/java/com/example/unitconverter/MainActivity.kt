package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // 앱의 배경
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Meters")
    }
    var outputUnit by remember {
        mutableStateOf("Meters")
    }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    var conversionFactor = remember {
        mutableStateOf(1.00)
    }
    var oConversionFactor = remember {
        mutableStateOf(1.00)
    }

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 32.sp,
        color = Color.Red
    )


    fun converUnits() {
        // ?: = elvis operator => 더블값이면 더블값을 그렇지 않으면 0.0을 사용해라
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result =
            (inputValueDouble * conversionFactor.value * 100 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {


        // UI 요소들이 하나씩 쌓인다.
        Text(text = "Unit Converter", style = customTextStyle)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = inputValue,
            onValueChange = {
                inputValue = it
                converUnits()
            }, label = { Text("Enter Value") })

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            // Input Box
            Box {
                // Input Button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    // 드롭다운 메뉴 아이템
                    DropdownMenuItem(
                        text = { Text("Centimeter") },
                        onClick = {
                            // 입력값이 센티미터로 바뀌어야 함 그리고 동시에 드롭다운 메뉴가 닫히게 해야함
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milimeters"
                            conversionFactor.value = 0.001
                            converUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            // Output Button
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }

                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    // 드롭다운 메뉴 아이템
                    DropdownMenuItem(
                        text = { Text("Centimeter") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeter"
                            oConversionFactor.value = 0.01
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.0
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            converUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Milimeters"
                            oConversionFactor.value = 0.001
                            converUnits()
                        }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Result : $outputValue $outputUnit",
                style = MaterialTheme.typography.headlineMedium
            )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}