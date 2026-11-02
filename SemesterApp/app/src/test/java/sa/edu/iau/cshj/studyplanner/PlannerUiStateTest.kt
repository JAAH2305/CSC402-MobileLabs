package sa.edu.iau.cshj.studyplanner

import org.junit.Assert.assertEquals
import org.junit.Test
import sa.edu.iau.cshj.studyplanner.model.sampleStudyTasks
import sa.edu.iau.cshj.studyplanner.ui.PlannerUiState

class PlannerUiStateTest {
    @Test fun progressTracksTaskCompletionWithoutChangingOriginalList() {
        val original = sampleStudyTasks()
        val updated = original.map { if (it.id == 2 || it.id == 4) it.copy(isDone = true) else it }
        assertEquals(5, PlannerUiState(tasks = updated).totalTasks)
        assertEquals(2, PlannerUiState(tasks = updated).doneTasks)
        assertEquals(0, PlannerUiState(tasks = original).doneTasks)
        assertEquals(listOf(1, 2, 3, 4, 5), updated.map { it.id })
    }

    @Test fun emptyListHasZeroProgress() {
        assertEquals(0, PlannerUiState().totalTasks)
        assertEquals(0, PlannerUiState().doneTasks)
    }
}
