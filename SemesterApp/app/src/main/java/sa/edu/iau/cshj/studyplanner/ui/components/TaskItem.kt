package sa.edu.iau.cshj.studyplanner.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sa.edu.iau.cshj.studyplanner.model.StudyTask

@Composable
fun TaskItem(
    task: StudyTask,
    onTaskClick: (Int) -> Unit,
    onDoneChange: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .clickable(onClickLabel = "Open task details") { onTaskClick(task.id) }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isDone,
            onCheckedChange = { checked -> onDoneChange(task.id, checked) },
            modifier = Modifier.semantics { contentDescription = "Completed: ${task.title}" }
        )
        Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp)) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium,
                textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None
            )
            Text(text = task.course, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true, name = "Task not done")
@Composable
private fun TaskItemNotDonePreview() {
    MaterialTheme {
        TaskItem(StudyTask(1, "Complete Lab 7", "CSC 402"), {}, { _, _ -> })
    }
}

@Preview(showBackground = true, name = "Task done")
@Composable
private fun TaskItemDonePreview() {
    MaterialTheme {
        TaskItem(StudyTask(1, "Complete Lab 7", "CSC 402", true), {}, { _, _ -> })
    }
}
