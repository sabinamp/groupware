package ch.fhnw.ip6.citycourier.ui

/**
 * Class defining the screens we have in the app: welcome, profile, team details and orders
 */
sealed class Screen {
    object WelcomeScr : Screen()
    data class ProfileScr(val courierId: String) : Screen()
    object OrdersScr : Screen()
    //object TeamScreen : Screen()
}

