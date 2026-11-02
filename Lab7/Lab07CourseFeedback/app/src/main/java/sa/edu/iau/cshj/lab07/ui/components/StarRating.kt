package sa.edu.iau.cshj.lab07.ui.components

import android.util.Log
import android.widget.RatingBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun StarRating(
    stars: Float,
    onStarsChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    // A View created once must still call the latest callback after recomposition.
    val currentOnStarsChange by rememberUpdatedState(onStarsChange)
    AndroidView(
        factory = { context ->
            Log.d("Lab07", "factory")
            RatingBar(context).apply {
                numStars = 5
                stepSize = 0.5f
                setOnRatingBarChangeListener { _, value, fromUser ->
                    if (fromUser) currentOnStarsChange(value)
                }
            }
        },
        update = { view ->
            Log.d("Lab07", "update $stars")
            view.rating = stars
        },
        modifier = modifier
    )
}
