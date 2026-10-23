package edu.iau.cshj.csc402.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AttendanceRoute(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// Task 2
data class AttendanceUiState(
    val sessionTitle: String = "",
    val checkedInCount: Int = 0,
    val isCheckedIn: Boolean = false
)

// Task 3
@Composable
fun AttendanceScreen(
    uiState: AttendanceUiState,
    onCheckIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = uiState.sessionTitle)

        Text(
            text = "Checked in: ${uiState.checkedInCount}"
        )

        Button(
            onClick = onCheckIn,
            enabled = !uiState.isCheckedIn
        ) {
            Text(text = "Check in")
        }
    }
}

// Task 4
@Composable
fun AttendanceRoute(
    modifier: Modifier = Modifier
) {
    var uiState by remember {
        mutableStateOf(
            AttendanceUiState(
                sessionTitle = "CSC 402 - Week 4",
                checkedInCount = 12
            )
        )
    }

    AttendanceScreen(
        uiState = uiState,
        onCheckIn = {
            uiState = uiState.copy(
                checkedInCount = uiState.checkedInCount + 1,
                isCheckedIn = true
            )
        },
        modifier = modifier
    )
}

@Preview(
    showBackground = true,
    name = "Not checked in yet"
)
@Composable
private fun NotCheckedInPreview() {
    MaterialTheme {
        AttendanceScreen(
            uiState = AttendanceUiState(
                sessionTitle = "CSC 402 - Week 4",
                checkedInCount = 12,
                isCheckedIn = false
            ),
            onCheckIn = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "Already checked in"
)
@Composable
private fun AlreadyCheckedInPreview() {
    MaterialTheme {
        AttendanceScreen(
            uiState = AttendanceUiState(
                sessionTitle = "CSC 402 - Week 4",
                checkedInCount = 12,
                isCheckedIn = true
            ),
            onCheckIn = {}
        )
    }
}