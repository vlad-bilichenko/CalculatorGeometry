package com.example.geometrycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.geometrycalculator.ui.theme.GeometryCalculatorTheme
import kotlin.math.PI
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeometryCalculatorTheme {
                GeometryCalculatorApp()
            }
        }
    }
}

@Composable
fun GeometryCalculatorApp() {
    var radius by remember { mutableStateOf("") }
    var areaResultCircle by remember { mutableStateOf("") }
    var side by remember { mutableStateOf("") }
    var areaResultSquare by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Калькулятор геометричних площ",
            style = MaterialTheme.typography.h5,
            color = Color.Red,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        CalculationCard(
            title = "Площа круга",
            inputFields = {
                TextField(
                    value = radius,
                    onValueChange = { radius = it },
                    label = { Text("Радіус") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            onCalculate = {
                areaResultCircle = if (radius.isNotEmpty()) {
                    val r = radius.toDoubleOrNull()
                    if (r != null && r > 0) {
                        "Площа круга: ${PI * r.pow(2)}"
                    } else {
                        "Радіус має бути додатним числом"
                    }
                } else {
                    "Введіть правильне значення радіуса"
                }
            },
            areaResult = areaResultCircle
        )

        Spacer(modifier = Modifier.height(8.dp))

        CalculationCard(
            title = "Площа квадрата",
            inputFields = {
                TextField(
                    value = side,
                    onValueChange = { side = it },
                    label = { Text("Сторона квадрата") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            onCalculate = {
                areaResultSquare = if (side.isNotEmpty()) {
                    val s = side.toDoubleOrNull()
                    if (s != null && s > 0) {
                        "Площа квадрата: ${s.pow(2)}"
                    } else {
                        "Сторона має бути додатним числом"
                    }
                } else {
                    "Введіть правильне значення сторони"
                }
            },
            areaResult = areaResultSquare
        )
    }
}

@Composable
fun CalculationCard(title: String, inputFields: @Composable () -> Unit, onCalculate: () -> Unit, areaResult: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color(0xFFE3F2FD)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            Text(title, style = MaterialTheme.typography.subtitle1, color = Color.Black)

            Spacer(modifier = Modifier.height(4.dp))

            inputFields()

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = areaResult,
                    modifier = Modifier.weight(1f),
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(4.dp))
                Button(
                    onClick = onCalculate,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text("Обчислити", color = Color.White)
                }
            }
        }
    }
}