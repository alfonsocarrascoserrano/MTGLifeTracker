package com.example.mtglifetracker.views

import android.graphics.ColorSpace
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.mtglifetracker.Data

val showSubmenu = mutableStateOf(0)
var rando = mutableStateOf(value = 0)

@Composable
fun menu(numplayers: Int, navController: NavHostController) {
    Box(){

        if(showSubmenu.value>0) {
            subMenus(numplayers)
        }

        Column(
            modifier = Modifier
                .background(Color.Gray)
                .fillMaxSize()
                .scale(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                Data.lifeList = mutableListOf(40, 40, 40, 40, 40, 40)
                navController.popBackStack()
            }) {
                Text(text = "Reset")
            }
            Button(onClick = {
                rando.value = (0 until numplayers).random()
                showSubmenu.value = 1
            }) {
                Text(text = "Random player")
            }
            Button(onClick = {
                showSubmenu.value = 2
            }) {
                Text(text = "Select colors")
            }
            Button(onClick = {
                showSubmenu.value = 3
            }) {
                Text(text = "Search Card")
            }
            Button(onClick = {
                rando.value = (0 until 2).random()
                showSubmenu.value = 4
            }) {
                Text(text = "Flip Coin")
            }
            Button(onClick = {
                rando.value = (1 until 21).random()
                showSubmenu.value = 5
            }) {
                Text(text = "D20")
            }

        }
    }
}

@Composable
fun subMenus(numplayers: Int) {
    Box(modifier = Modifier
        .zIndex(1f)
        .fillMaxSize()
        //.clip(RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)))
    ) {
        when (showSubmenu.value) {
            1 -> { //Random player
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .fillMaxHeight(0.2f)
                        .background(Color.White)
                        .align(Alignment.Center)
                    ,verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row() {
                        Text(
                            text = "Player " + (rando.value + 1).toString(),
                            fontSize = 40.sp
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Box(
                            modifier = Modifier
                                .background(Data.colorList[rando.value])
                                .size(30.dp, 30.dp)
                                .align(alignment = Alignment.CenterVertically)
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(modifier = Modifier.padding(10.dp), onClick = {
                            rando.value = (0 until numplayers).random()
                        }) {
                            Text("Again")
                        }
                        Button(modifier = Modifier.padding(10.dp), onClick = {
                            showSubmenu.value = 0
                        }) {
                            Text("Close")
                        }
                    }
                }
            }
            2 -> { //Select colors
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .fillMaxHeight(0.1f + 0.065f * numplayers)
                        .background(Color.White)
                        .align(Alignment.Center)
                    ,verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row() {
                        Text(
                            text = "Player 1 " ,
                            fontSize = 40.sp
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Button(modifier = Modifier
                            .size(30.dp, 30.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[0]),
                            onClick = {
                            //showSubmenu.value = 0
                                Data.colorList[0] = Color.White //TO DO
                        }) {
                        }
                    }
                    Row() {
                        Text(
                            text = "Player 2 " ,
                            fontSize = 40.sp
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Button(modifier = Modifier
                            .size(30.dp, 30.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[1]),
                            onClick = {
                                //showSubmenu.value = 0
                            }) {
                        }
                    }
                    if(numplayers>2){
                        Row() {
                            Text(
                                text = "Player 3 " ,
                                fontSize = 40.sp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Button(modifier = Modifier
                                .size(30.dp, 30.dp)
                                .align(alignment = Alignment.CenterVertically),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[2]),
                                onClick = {
                                    //showSubmenu.value = 0
                                }) {
                            }
                        }
                    }
                    if(numplayers>3){
                        Row() {
                            Text(
                                text = "Player 4 " ,
                                fontSize = 40.sp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Button(modifier = Modifier
                                .size(30.dp, 30.dp)
                                .align(alignment = Alignment.CenterVertically),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[3]),
                                onClick = {
                                    //showSubmenu.value = 0
                                }) {
                            }
                        }
                    }
                    if(numplayers>4){
                        Row() {
                            Text(
                                text = "Player 5 " ,
                                fontSize = 40.sp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Button(modifier = Modifier
                                .size(30.dp, 30.dp)
                                .align(alignment = Alignment.CenterVertically),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[4]),
                                onClick = {
                                    //showSubmenu.value = 0
                                }) {
                            }
                        }
                    }
                    if(numplayers==6){
                        Row() {
                            Text(
                                text = "Player 6 " ,
                                fontSize = 40.sp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Button(modifier = Modifier
                                .size(30.dp, 30.dp)
                                .align(alignment = Alignment.CenterVertically),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[5]),
                                onClick = {
                                    //showSubmenu.value = 0
                                }) {
                            }
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(modifier = Modifier.padding(10.dp), onClick = {
                            showSubmenu.value = 0
                        }) {
                            Text("Close")
                        }
                    }
                }
            }
            3 -> { //Search card

            }
            4 -> { //Flip coin
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .fillMaxHeight(0.2f)
                        .background(Color.White)
                        .align(Alignment.Center)
                    ,verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row() {
                        Text(
                            text = "Result is " + (if(rando.value==1) "Heads" else "Tails"),
                            fontSize = 40.sp
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(modifier = Modifier.padding(10.dp), onClick = {
                            rando.value = (0 until 2).random()
                        }) {
                            Text("Again")
                        }
                        Button(modifier = Modifier.padding(10.dp), onClick = {
                            showSubmenu.value = 0
                        }) {
                            Text("Close")
                        }
                    }
                }
            }
            5 -> { //D20
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.65f)
                        .fillMaxHeight(0.2f)
                        .background(Color.White)
                        .align(Alignment.Center)
                    ,verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row() {
                        Text(
                            text = "Result is " + (rando.value).toString(),
                            fontSize = 40.sp
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(modifier = Modifier.padding(10.dp), onClick = {
                            rando.value = (1 until 21).random()
                        }) {
                            Text("Again")
                        }
                        Button(modifier = Modifier.padding(10.dp), onClick = {
                            showSubmenu.value = 0
                        }) {
                            Text("Close")
                        }
                    }
                }
            }
        }
    }
}