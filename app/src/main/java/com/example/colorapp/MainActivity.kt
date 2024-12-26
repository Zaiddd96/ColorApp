package com.example.colorapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.colorapp.ui.theme.ColorAppTheme
import com.example.colorapp.ui.theme.ColorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorApp()
        }
    }
}

@Composable
fun ColorApp(
    viewModel: ColorViewModel = viewModel(
        factory = ColorViewModelFactory()
    )
) {
    val colors by viewModel.colors.collectAsState(initial = emptyList())
    val unsyncedCount by viewModel.unsyncedCount.collectAsState(initial = 0)

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { viewModel.addRandomColor() }) {
                    Text("Add Color")
                }

                Button(
                    onClick = {  }
                ) {
                    Row {
                        Text("Sync")
                        if (unsyncedCount > 0) {
                            Text(" ($unsyncedCount)")
                        }
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(colors) { colorEntry ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = android.graphics.Color.parseColor(colorEntry.color).toComposeColor()
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(colorEntry.color)
                            Text("${colorEntry.timestamp}")
                        }
                    }
                }
            }
        }
    }
}

// Extension function to convert Android Color to Compose Color
fun Int.toComposeColor() = androidx.compose.ui.graphics.Color(this)