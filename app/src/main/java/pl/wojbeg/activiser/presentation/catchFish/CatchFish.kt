package pl.wojbeg.activiser.presentation.catchFish

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatchFishScreen (
    navController: NavController
) {
    val context = LocalContext.current
    val backgroundImage = "https://thumbs.dreamstime.com/z/w%C4%99dkowanie-na-jeziorze-konceptualne-zdj%C4%99cie-z-w%C4%99dk%C4%85-194928204.jpg"
    val fishImage = "https://cdn.pixabay.com/photo/2016/12/12/05/36/goldfish-1900832_1280.png"

    val fishOffset = remember { Animatable(0f) }
    val fishTransition = rememberInfiniteTransition()

    fun isFishCaught(): Boolean {
        val fishX = fishOffset.value * 200 - 100
        val frameLeftX = -25.0
        val frameRightX = 25.0

        return fishX in frameLeftX..frameRightX
    }

    LaunchedEffect(Unit) {
        fishOffset.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 500, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "CatchFish") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Cyan)
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.TopCenter,
            ) {
                Image(
                    painter = rememberAsyncImagePainter(backgroundImage),
                    contentDescription = "Your image",
                    modifier = Modifier.fillMaxSize()
                )

                Text(
                    text = "Spróbuj złapać rybę!",
                    fontSize = 16.sp,
                    color = Color.Blue
                )

                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                    ) {
                        drawLine(
                            color = Color.Red,
                            start = Offset(0f, size.height / 2),
                            end = Offset(size.width, size.height / 2),
                            strokeWidth = 35f
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(x = with(LocalDensity.current) { (fishOffset.value * 200 - 100).dp }),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(fishImage),
                        contentDescription = "Your image",
                        modifier = Modifier.fillMaxSize(0.2f)
                    )
                }

                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier
                        .height(100.dp)
                        .width(50.dp)
                        .border(5.dp, Color.Black, RoundedCornerShape(5.dp)),
                    )
                }

                Box(modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        onClick = {
                            if (isFishCaught())
                                Toast.makeText(context, "Wygrałeś! Twoją nagrodą jest ta oferta promocyjna: ", Toast.LENGTH_SHORT).show()
                            else
                                Toast.makeText(context, "Przegrałeś próbuj jeszcze raz!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .padding(16.dp)
                            .height(48.dp)
                            .width(160.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA500)),
                        contentPadding = PaddingValues(12.dp)
                    ) {
                        Text(text = "Catch!")
                    }
                }
            }
        }

    }

}
