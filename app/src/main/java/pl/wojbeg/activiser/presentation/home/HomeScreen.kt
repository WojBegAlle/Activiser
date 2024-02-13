package pl.wojbeg.activiser.presentation.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import pl.wojbeg.activiser.domain.models.MinigameCategory
import pl.wojbeg.activiser.presentation.util.Screen

@Composable
fun HomeScreen (
    navController: NavController
) {
    val itemList = listOf<MinigameCategory>(
        MinigameCategory("Ice Breaker", Screen.IceBreakScreen, "https://www.sklep-militarny.com.pl/images/snow_art/snieg_materialy_328-2.jpg"),
        MinigameCategory("Catch Fish", Screen.CatchFish, "https://imageio.forbes.com/specials-images/imageserve/615f7a84c4048b29616d55d6/A-person-fishing-from-a-boat-at-Islands-of-Loreto-/960x0.jpg?format=jpg&width=960"),
        MinigameCategory("Ice Breaker", Screen.IceBreakScreen, ""),
        MinigameCategory("Ice Breaker", Screen.IceBreakScreen, ""),
    )

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(5.dp)
    ) {
        items(itemList.size) { index ->
            RoundedSquare(
                itemList[index],
                color = when (index % 3) {
                    0 -> Color.Red
                    1 -> Color.Green
                    else -> Color.Blue
                },
                onClick = {
                    navController.navigate(itemList[index].route.route)
                }
            )
        }
    }

}

@Composable
fun RoundedSquare(minigameCategory: MinigameCategory, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(120.dp)
            .padding(4.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(4.dp, color),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = rememberAsyncImagePainter(minigameCategory.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp)
                    .background(color, shape = RoundedCornerShape(16.dp))
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.DarkGray),
                        startY = 200f
                    )
                ))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 4.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = minigameCategory.name,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                )
            }
        }
    }
}
