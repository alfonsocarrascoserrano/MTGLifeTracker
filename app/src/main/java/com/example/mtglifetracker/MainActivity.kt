package com.example.mtglifetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mtglifetracker.views.*

class Data {
    companion object Num {
        var lifeList: MutableList<Int> by mutableStateOf(mutableListOf(40,40,40,40,40,40))
        var colorList: MutableList<Color> by mutableStateOf(mutableListOf(
            Color(0xFF1b85b8),
            Color(0xFF5a5255),
            Color(0xFF559e83),
            Color(0xFFae5a41),
            Color(0xFFc3cb71),
            Color.Gray))
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, "start"){
                composable("start"){
                    start(navController)
                }
                composable("menu/{numplayers}",
                    arguments = listOf(navArgument("numplayers")
                    { type = NavType.IntType})
                ){backStackEntry ->
                    val numplayers = backStackEntry.arguments?.getInt("numplayers")
                    requireNotNull(numplayers)
                    menu(numplayers, navController)
                }
                composable(route="table/{numplayers}",
                    arguments = listOf(navArgument("numplayers")
                        { type = NavType.IntType})
                ){backStackEntry ->
                    val numplayers = backStackEntry.arguments?.getInt("numplayers")
                    requireNotNull(numplayers)
                    players(numplayers, navController)
                }
            }
            /*MTGLifeTrackerTheme {
                Surface(
                    //color = MaterialTheme.colors.background
                ) {

                }
            }*/
        }
    }
}

