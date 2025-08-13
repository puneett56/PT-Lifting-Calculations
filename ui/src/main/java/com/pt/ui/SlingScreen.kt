package com.pt.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pt.coremath.Sling

@Composable
fun SlingScreen(onBack: () -> Unit) {
    var weight by remember { mutableStateOf("1000") }
    var angle by remember { mutableStateOf("60") }
    var result by remember { mutableStateOf<Double?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sling Tension") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Back") } }
            )
        }
    ) { padding ->
        Column(
            Modifier.padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(weight, { weight = it }, label = { Text("Load weight (unitless)") })
            OutlinedTextField(angle, { angle = it }, label = { Text("Angle from horizontal (deg)") })
            Button(onClick = {
                val w = weight.toDoubleOrNull()
                val a = angle.toDoubleOrNull()
                if (w != null && a != null) result = Sling.symmetricTwoLegTensionPerLeg(w, a)
            }) { Text("Calculate") }
            result?.let {
                Text("Per-leg tension: ${"%.2f".format(it)}")
                Text("Angle factor: ${"%.3f".format(1.0 / (2.0 * kotlin.math.cos(Math.toRadians(angle.toDoubleOrNull() ?: 0.0))))}")
                Text("Note: For 3–4 legs, only two may be fully loaded unless geometry proves otherwise.")
            }
        }
    }
}
