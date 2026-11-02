package sa.edu.iau.cshj.studyplanner.ui

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.model.StudyTask
import sa.edu.iau.cshj.studyplanner.model.sampleStudyTasks

@Composable
fun TaskDetailScreen(
    task: StudyTask?,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Task details")
        if (task == null) {
            Text("Task not found")
        } else {
            Text(task.title)
            Text(task.course)
            Button(onClick = onShareClick) { Text("Share task") }
        }
        Button(onClick = onBackClick) { Text("Back") }
    }
}

@Composable
fun TaskDetailRoute(taskId: Int, onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    // Titles and courses are fixed in Part 4; completion state stays in PlannerHomeRoute.
    val task = sampleStudyTasks().find { it.id == taskId }
    TaskDetailScreen(
        task = task,
        onBackClick = onBackClick,
        onShareClick = {
            if (task != null) {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "${task.title} — ${task.course}")
                }
                context.startActivity(Intent.createChooser(intent, "Share task"))
            }
        },
        modifier = modifier
    )
}
