package com.pt.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pt.coremath.GBP
import com.pt.coremath.GbpPointResult
import com.pt.coremath.Mat
import com.pt.coremath.OutriggerPad
import com.pt.coremath.Soil

@Composable
fun GBPOutriggerScreen(onBack: () -> Unit) {
    var reactions by remember { mutableStateOf("120, 130, 110, 140") } // kN
    var padW by remember { mutableStateOf("0.5") }  // m
    var padL by remember { mutableStateOf("0.5") }  // m
    var matW by remember { mutableStateOf("1.2") }  // m
    var matL by remember { mutableStateOf("1.2") }  // m
    var soilAllow by remember { mutableStateOf("200") } // kPa
    var utilLimit by remember { mutableStateOf("0.8") }

    var results by remember { mutableStateOf<List<GbpPointResult>>(emptyList()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ground Bearing Pressure - Outrigger") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Back") } }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedTextField(reactions, { reactions = it }, label = { Text("Outrigger reactions (kN, comma-separated)") })
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(padW, { padW = it }, label = { Text("Pad W (m)") }, modifier = Modifier.weight(1f))
                OutlinedTextField(padL, { padL = it }, label = { Text("Pad L (m)") }, modifier = Modifier.weight(1f))
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(matW, { matW = it }, label = { Text("Mat W (m)") }, modifier = Modifier.weight(1f))
                OutlinedTextField(matL, { matL = it }, label = { Text("Mat L (m)") }, modifier = Modifier.weight(1f))
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(soilAllow, { soilAllow = it }, label = { Text("Allowable Soil (kPa)") }, modifier = Modifier.weight(1f))
                OutlinedTextField(utilLimit, { utilLimit = it }, label = { Text("Utilization limit (e.g., 0.8)") }, modifier = Modifier.weight(1f))
            }
            Button(onClick = {
                val list = reactions.split(",").mapNotNull { it.trim().toDoubleOrNull() }
                val pad = OutriggerPad(padW.toDoubleOrNull() ?: 0.5, padL.toDoubleOrNull() ?: 0.5)
                val mat = Mat(matW.toDoubleOrNull() ?: 1.2, matL.toDoubleOrNull() ?: 1.2)
                val soil = Soil((soilAllow.toDoubleOrNull() ?: 200.0))
                val limit = utilLimit.toDoubleOrNull() ?: 0.8
                results = GBP.outrigger(list, pad, mat, soil, limit)
            }) { Text("Calculate") }

            results.takeIf { it.isNotEmpty() }?.let { res ->
                val max = res.maxBy { it.pressureKPa }
                Text("Max pressure: ${"%.1f".format(max.pressureKPa)} kPa; Allowable: ${"%.1f".format(max.allowableKPa)} kPa; Util: ${"%.2f".format(max.utilization)}; Pass: ${max.pass}")
                Spacer(Modifier.height(8.dp))
                res.forEachIndexed { idx, r ->
                    Text("Outrigger ${idx + 1}: P=${"%.1f".format(r.pressureKPa)} kPa, Area=${"%.2f".format(r.areaM2)} m², Required A=${"%.2f".format(r.requiredAreaM2)} m², Pass=${r.pass}")
                }
                Text("Tip: Increase mat size to reduce pressure. Validate with OEM reactions and geotechnical report.")
            }
        }
    }
}
