package pl.wojbeg.activiser.presentation.util

sealed class Screen(val route: String) {
    data object HomeScreen: Screen("home")
    data object IceBreakScreen: Screen("ice_break_screen")
    data object CatchFish: Screen("catch_fish")
}