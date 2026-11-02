package sa.edu.iau.cshj.studyplanner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import sa.edu.iau.cshj.studyplanner.ui.PlannerHomeRoute
import sa.edu.iau.cshj.studyplanner.ui.TaskDetailRoute

@Serializable object PlannerHome
@Serializable data class TaskDetail(val taskId: Int)

@Composable
fun PlannerApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PlannerHome, modifier = modifier) {
        composable<PlannerHome> {
            PlannerHomeRoute(onTaskClick = { taskId ->
                navController.navigate(TaskDetail(taskId))
            })
        }
        composable<TaskDetail> { entry ->
            TaskDetailRoute(
                taskId = entry.toRoute<TaskDetail>().taskId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
