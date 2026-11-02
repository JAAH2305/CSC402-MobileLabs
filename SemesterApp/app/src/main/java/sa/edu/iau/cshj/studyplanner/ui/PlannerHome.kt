package sa.edu.iau.cshj.studyplanner.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.model.StudyTask
import sa.edu.iau.cshj.studyplanner.model.sampleStudyTasks
import sa.edu.iau.cshj.studyplanner.ui.components.TaskItem

// Save only Bundle-compatible strings, preserving the list across rotation.
private val StudyTasksSaver = listSaver<List<StudyTask>, String>(
    save = { tasks ->
        tasks.flatMap { listOf(it.id.toString(), it.title, it.course, it.isDone.toString()) }
    },
    restore = { values ->
        values.chunked(4).map {
            StudyTask(id = it[0].toInt(), title = it[1], course = it[2], isDone = it[3].toBoolean())
        }
    }
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskClick: (Int) -> Unit,
    onDoneChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Hello, ${uiState.studentName}!", style = MaterialTheme.typography.headlineSmall)
        Text("${uiState.doneTasks} of ${uiState.totalTasks} done")
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(uiState.tasks, key = { it.id }) { task ->
                TaskItem(task = task, onTaskClick = onTaskClick, onDoneChange = onDoneChange)
            }
        }
    }
}

@Composable
fun PlannerHomeRoute(onTaskClick: (Int) -> Unit, modifier: Modifier = Modifier) {
    var tasks by rememberSaveable(stateSaver = StudyTasksSaver) {
        mutableStateOf(sampleStudyTasks())
    }
    PlannerHomeScreen(
        uiState = PlannerUiState(tasks = tasks),
        onTaskClick = onTaskClick,
        onDoneChange = { id, done ->
            tasks = tasks.map { if (it.id == id) it.copy(isDone = done) else it }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true, name = "Planner with five tasks")
@Composable
private fun PlannerHomeFiveTasksPreview() {
    MaterialTheme {
        PlannerHomeScreen(
            uiState = PlannerUiState(tasks = sampleStudyTasks()),
            onTaskClick = {},
            onDoneChange = { _, _ -> }
        )
    }
}
