package com.example.project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project1.ui.theme.Project1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1Theme {
                YatriMitraApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun YatriMitraApp(viewModel: YatriMitraViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Yatri-Mitra Tracker", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // ETA Display Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Next Shared Auto In",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "${uiState.eta} sec",
                        style = MaterialTheme.typography.displayMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Text(
                text = "Live Route Simulation",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp)
            )

            // Route Map Simulation
            RouteMap(
                progress = uiState.progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 24.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Status Info
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Red)
                Text(
                    text = "Route: Town Center to College Gate",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
fun RouteMap(progress: Float, modifier: Modifier = Modifier) {
    val routeColor = MaterialTheme.colorScheme.outlineVariant
    val vehicleColor = MaterialTheme.colorScheme.primary
    val stopColor = Color.Red

    Canvas(modifier = modifier) {
        val width = size.width
        val centerY = size.height / 2

        // Draw Route Line
        drawLine(
            color = routeColor,
            start = Offset(0f, centerY),
            end = Offset(width, centerY),
            strokeWidth = 8.dp.toPx()
        )

        // Draw Stops
        drawCircle(
            color = routeColor,
            radius = 6.dp.toPx(),
            center = Offset(0f, centerY)
        )
        
        // Draw User's Stop (at the end)
        drawCircle(
            color = stopColor,
            radius = 8.dp.toPx(),
            center = Offset(width, centerY)
        )

        // Draw Vehicle Marker (Auto)
        val vehicleX = width * progress
        drawCircle(
            color = vehicleColor,
            radius = 12.dp.toPx(),
            center = Offset(vehicleX, centerY),
            style = Fill
        )
        
        // Add a small triangle to make it look like a vehicle/pointer
        val trianglePath = androidx.compose.ui.graphics.Path().apply {
            moveTo(vehicleX - 10.dp.toPx(), centerY - 15.dp.toPx())
            lineTo(vehicleX + 10.dp.toPx(), centerY - 15.dp.toPx())
            lineTo(vehicleX, centerY - 25.dp.toPx())
            close()
        }
        drawPath(trianglePath, color = vehicleColor)
    }
}

@Preview(showBackground = true)
@Composable
fun YatriMitraPreview() {
    Project1Theme {
        YatriMitraApp()
    }
}