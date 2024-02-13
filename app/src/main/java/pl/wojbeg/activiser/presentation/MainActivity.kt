package pl.wojbeg.activiser.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import pl.wojbeg.activiser.presentation.catchFish.CatchFishScreen
import pl.wojbeg.activiser.presentation.home.HomeScreen
import pl.wojbeg.activiser.presentation.iceBreak.IceBreakScreen
import pl.wojbeg.activiser.presentation.ui.theme.ActiviserTheme
import pl.wojbeg.activiser.presentation.util.Screen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActiviserTheme {
                AppNavigator()
            }
        }
    }

    @Composable
    private fun AppNavigator() {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
            composable(Screen.HomeScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(Screen.IceBreakScreen.route) {
                IceBreakScreen(navController = navController)
            }
            composable(Screen.CatchFish.route) {
                CatchFishScreen(navController = navController)
            }
        }
    }
}
