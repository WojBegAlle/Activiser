package pl.wojbeg.activiser.presentation.iceBreak

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IceBreakScreen (
    navController: NavController
) {
    val context = LocalContext.current
    val CLICK_COEFFICIENT = 5

    val imagesRange = 0..10
    var clickCount by remember { mutableStateOf(0) }
    var images by remember { mutableStateOf(imagesRange.toList().shuffled()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "IceBreaker") },
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
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
                contentAlignment = Alignment.TopCenter,
            ) {
                val imageUrl = "https://mm3d.pl/eng_pl_Original-Prusa-i3-MK3S-3D-Printer-169_1.jpg"
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize(0.5f)
                        .padding(4.dp)
                )
                for (imgNum in images)
                    IceShardImage(imageIndex = imgNum, context = context) {
                        clickCount++
                        if (clickCount >= CLICK_COEFFICIENT) {
                            images = images.filter { it -> it != imgNum }
                            clickCount = 0
                        }
                    }
            }
            
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(top = 6.dp),
                contentAlignment = Alignment.TopCenter,
            ) {
                Text(text = "Rozbij lód i odkryj wspaniałe oferty!")
            }
        }
    }
}

@Composable
fun IceShardImage(imageIndex: Int, context: Context, onClick: () -> Unit) {
    val resourceId = context.resources.getIdentifier("ice_shard_$imageIndex", "drawable", context.packageName)

    Image(
        modifier = Modifier
            .clickable { onClick() },
        painter = painterResource(resourceId),
        contentDescription = null
    )
}
