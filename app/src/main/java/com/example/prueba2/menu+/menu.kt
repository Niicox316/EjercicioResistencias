package com.example.prueba2.menu

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Menu() {
    val context = LocalContext.current

    // Variables de estado para las bandas de colores y la resistencia
    var banda1 by remember { mutableStateOf<String?>(null) }
    var banda2 by remember { mutableStateOf<String?>(null) }
    var multiplicador by remember { mutableStateOf<String?>(null) }
    var tolerancia by remember { mutableStateOf<String?>(null) }

    // Variables de estado para el estado de los dropdowns
    var isExpanded1 by remember { mutableStateOf(false) }
    var isExpanded2 by remember { mutableStateOf(false) }
    var isExpanded3 by remember { mutableStateOf(false) }
    var isExpanded4 by remember { mutableStateOf(false) }

    var resistencia by remember { mutableStateOf("") } // Resistencia calculada

    // Listas de colores y valores para las bandas de colores
    val bandasColores = listOf(
        Pair("Negro", Color.Black),
        Pair("Marrón", Color(0xFF8B4513)),
        Pair("Rojo", Color.Red),
        Pair("Naranja", Color(0xFFFFA500)),
        Pair("Amarillo", Color.Yellow),
        Pair("Verde", Color.Green),
        Pair("Azul", Color.Blue),
        Pair("Violeta", Color(0xFF8A2BE2)),
        Pair("Gris", Color.Gray),
        Pair("Blanco", Color.White)
    )

    val valoresBandas = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9) // Valores de las bandas

    // Lista de colores y porcentajes para tolerancia
    val toleranciasColores = listOf(
        Pair("Gris", "0.05%"),
        Pair("Dorado", "5%"),
        Pair("Plateado", "10%")
    )

    // Función para calcular la resistencia
    fun calcularResistencia() {
        // Verificar que todas las bandas y la tolerancia estén seleccionadas
        if (banda1 != null && banda2 != null && multiplicador != null && tolerancia != null) {
            val valorBanda1 = banda1!!.toInt()
            val valorBanda2 = banda2!!.toInt()
            val valorMultiplicador = multiplicador!!.toFloat()

            // Calcular el valor de la resistencia
            val valorResistencia = (valorBanda1 * 10 + valorBanda2) * valorMultiplicador
            resistencia = "$valorResistencia Ω $tolerancia" // Formato de salida
        } else {
            resistencia = "Seleccione todas las bandas y tolerancia"
        }
    }

    // Diseño del menú
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)  // Fondo blanco
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Centrar verticalmente
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Título del programa
        Text(
            text = "Calculadora de Resistencias",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))  // Espacio bajo el título

        // Dropdown para Banda 1
        ExposedDropdownMenuBox(
            expanded = isExpanded1,
            onExpandedChange = { isExpanded1 = !isExpanded1 }
        ) {
            TextField(
                value = banda1 ?: "Banda 1",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded1) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded1,
                onDismissRequest = { isExpanded1 = false }
            ) {
                bandasColores.forEachIndexed { index, (nombre, color) ->
                    DropdownMenuItem(
                        text = {
                            Row {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(color)
                                )
                                Text("  $nombre - $index") // Muestra el nombre y el índice
                            }
                        },
                        onClick = {
                            banda1 = "${valoresBandas[index]}" // Asigna el valor seleccionado
                            isExpanded1 = false
                            Toast.makeText(context, "Seleccionaste Banda 1: $nombre", Toast.LENGTH_SHORT).show()
                            calcularResistencia() // Calcula la resistencia al seleccionar
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown para Banda 2
        ExposedDropdownMenuBox(
            expanded = isExpanded2,
            onExpandedChange = { isExpanded2 = !isExpanded2 }
        ) {
            TextField(
                value = banda2 ?: "Banda 2",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded2) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded2,
                onDismissRequest = { isExpanded2 = false }
            ) {
                bandasColores.forEachIndexed { index, (nombre, color) ->
                    DropdownMenuItem(
                        text = {
                            Row {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .background(color)
                                )
                                Text("  $nombre - $index") // Muestra el nombre y el índice
                            }
                        },
                        onClick = {
                            banda2 = "${valoresBandas[index]}" // Asigna el valor seleccionado
                            isExpanded2 = false
                            Toast.makeText(context, "Seleccionaste Banda 2: $nombre", Toast.LENGTH_SHORT).show()
                            calcularResistencia() // Calcula la resistencia al seleccionar
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown para el Multiplicador
        ExposedDropdownMenuBox(
            expanded = isExpanded3,
            onExpandedChange = { isExpanded3 = !isExpanded3 }
        ) {
            TextField(
                value = multiplicador ?: "Multiplicador",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded3) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded3,
                onDismissRequest = { isExpanded3 = false }
            ) {
                // Opciones de multiplicadores
                val multiplicadores = listOf("1", "10", "100", "1k", "10k", "100k", "1M", "10M")
                multiplicadores.forEachIndexed { index, valor ->
                    DropdownMenuItem(
                        text = { Text(" $valor") }, // Muestra el valor del multiplicador
                        onClick = {
                            multiplicador = valor.replace("k", "000").replace("M", "000000") // Formateo
                            isExpanded3 = false
                            Toast.makeText(context, "Seleccionaste Multiplicador: $valor", Toast.LENGTH_SHORT).show()
                            calcularResistencia() // Calcula la resistencia al seleccionar
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown para la Tolerancia
        ExposedDropdownMenuBox(
            expanded = isExpanded4,
            onExpandedChange = { isExpanded4 = !isExpanded4 }
        ) {
            TextField(
                value = tolerancia ?: "Tolerancia",
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(isExpanded4) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = isExpanded4,
                onDismissRequest = { isExpanded4 = false }
            ) {
                toleranciasColores.forEach { (nombre, valor) ->
                    DropdownMenuItem(
                        text = { Text(" $nombre - $valor") }, // Muestra el color y el valor de tolerancia
                        onClick = {
                            tolerancia = valor // Asigna la tolerancia seleccionada
                            isExpanded4 = false
                            Toast.makeText(context, "Seleccionaste Tolerancia: $nombre", Toast.LENGTH_SHORT).show()
                            calcularResistencia() // Calcula la resistencia al seleccionar
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Mostrar el dibujo de la resistencia
        Canvas(
            modifier = Modifier.size(150.dp) // Aumenta el tamaño del canvas
        ) {
            val color1 = bandasColores.getOrNull(banda1?.toInt() ?: 0)?.second ?: Color.Transparent
            val color2 = bandasColores.getOrNull(banda2?.toInt() ?: 0)?.second ?: Color.Transparent
            val colorMultiplier = bandasColores.getOrNull(multiplicador?.toInt() ?: 0)?.second ?: Color.Transparent

            // Dibujo de las bandas
            drawRoundRect(color = color1, size = size.copy(width = 40f, height = 30f), topLeft = Offset(0f, 20f), cornerRadius = CornerRadius(5f))
            drawRoundRect(color = color2, size = size.copy(width = 40f, height = 30f), topLeft = Offset(50f, 20f), cornerRadius = CornerRadius(5f))
            drawRoundRect(color = colorMultiplier, size = size.copy(width = 40f, height = 30f), topLeft = Offset(100f, 20f), cornerRadius = CornerRadius(5f))

            // Dibujo de la resistencia
            drawLine(color = Color.Gray, strokeWidth = 10f, start = Offset(40f, 30f), end = Offset(50f, 30f))
            drawLine(color = Color.Gray, strokeWidth = 10f, start = Offset(90f, 30f), end = Offset(100f, 30f))
        }


        // Mostrar la resistencia calculada centrada
        Text(
            text = resistencia,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally) // Centrar texto
        )
    }
}
