package sa.edu.iau.cshj.studyplanner.model

data class StudyTask(
    val id: Int,
    val title: String,
    val course: String,
    val isDone: Boolean = false
)

fun sampleStudyTasks(): List<StudyTask> = listOf(
    StudyTask(1, "Complete Lab 7", "CSC 402"),
    StudyTask(2, "Review navigation", "CSC 402"),
    StudyTask(3, "Prepare the interface evaluation", "HCI"),
    StudyTask(4, "Practice MIPS instructions", "Computer Architecture"),
    StudyTask(5, "Review image filters", "Image Processing")
)
