package framework.screens

import framework.screens.homeScreen.HomeScreen
import framework.screens.homeScreen.HomeScreenAndroid
import org.junit.jupiter.api.fail

val screen = when (System.getProperty("platform")) {
    "Android" -> AndroidPageFactory
//    "iOS" -> IOSPageFactory
    else -> fail("Wrong screen")
}

interface ScreenFactory {
    val home: HomeScreen
}

object AndroidPageFactory : ScreenFactory {
    override val home = HomeScreenAndroid()
}

//object IOSPageFactory : ScreenFactory {
//
//}