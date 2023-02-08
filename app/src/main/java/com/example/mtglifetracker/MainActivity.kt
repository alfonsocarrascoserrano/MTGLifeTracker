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
        var pColor: MutableList<Int> by mutableStateOf(mutableListOf(0,1,2,3,4,5))
        val colorList:List<Color> = listOf(
            Color(0xFFc3cb71),
            Color(0xFF1b85b8),
            Color(0xFF5a5255),
            Color(0xFFae5a41),
            Color(0xFF559e83),
            Color(0xFF8F8F63),
            Color(0xFFB99359),
            Color(0xFF8CB57A),
            Color(0xFF3B6C87),
            Color(0xFF65707D),
            Color(0xFF84564B),
            Color(0xFFE1E5B8)
        )
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

