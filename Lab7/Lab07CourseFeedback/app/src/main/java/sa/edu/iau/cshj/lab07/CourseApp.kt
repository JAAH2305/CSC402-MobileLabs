package sa.edu.iau.cshj.lab07

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import sa.edu.iau.cshj.lab07.ui.CampusNewsRoute
import sa.edu.iau.cshj.lab07.ui.CourseRatingRoute

@Serializable object Home
@Serializable data class CourseRating(val code: String)
@Serializable object CampusNews

@Composable
fun HomeScreen(
    onRateClick: () -> Unit,
    onNewsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Course Feedback")
        Button(onClick = onRateClick) { Text("Rate CSC 402") }
        Button(onClick = onNewsClick) { Text("Campus news") }
    }
}

@Composable
fun HomeRoute(
    onRateClick: () -> Unit,
    onNewsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    HomeScreen(onRateClick, onNewsClick, modifier)
}

@Composable
fun CourseApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home, modifier = modifier) {
        composable<Home> {
            HomeRoute(
                onRateClick = { navController.navigate(CourseRating("CSC 402")) },
                onNewsClick = { navController.navigate(CampusNews) }
            )
        }
        composable<CourseRating> { entry ->
            CourseRatingRoute(code = entry.toRoute<CourseRating>().code)
        }
        composable<CampusNews> {
            CampusNewsRoute(onBackClick = { navController.popBackStack() })
        }
    }
}
