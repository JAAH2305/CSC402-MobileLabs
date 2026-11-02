package sa.edu.iau.cshj.studyplanner.ui

import sa.edu.iau.cshj.studyplanner.model.StudyTask

data class PlannerUiState(
    val studentName: String = "Jumana",
    val tasks: List<StudyTask> = emptyList()
) {
    val totalTasks: Int get() = tasks.size
    val doneTasks: Int get() = tasks.count { it.isDone }
}
