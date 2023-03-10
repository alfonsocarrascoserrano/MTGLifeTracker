package com.example.mtglifetracker.views

import android.annotation.SuppressLint
import android.graphics.ColorSpace
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil.size.Size
import com.example.mtglifetracker.Data
import com.example.mtglifetracker.ui.theme.Shapes

val showSubmenu = mutableStateOf(0)
val rando = mutableStateOf(value = 0)
val randoType = mutableStateOf(value = 0)
val colorpicker = mutableStateOf(value = 0)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun menu(numplayers: Int, navController: NavHostController) {
    Box(){
        if(showSubmenu.value>0) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(onClick = { showSubmenu.value=0 }) {
                                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
                            }
                        },
                        title = {
                            Text("")
                        }
                    )
                }
            ) { subMenus(numplayers)}
        } else {
            Scaffold(
                topBar = {
                    TopAppBar(
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
                            }
                        },
                        title = {
                            Text("")
                        }
                    )
                }
            ) {

                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .fillMaxSize()
                        .scale(2f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(modifier = Modifier.fillMaxWidth(0.4f),
                        onClick = {
                            Data.lifeList = mutableListOf(40, 40, 40, 40, 40, 40)
                            navController.popBackStack()
                        }) {
                        Text(text = "Reset")
                    }
                    Button(modifier = Modifier.fillMaxWidth(0.4f),
                        onClick = {
                            showSubmenu.value = 1
                        }) {
                        Text(text = "Randomness")
                    }
                    Button(modifier = Modifier.fillMaxWidth(0.4f),
                        onClick = {
                            showSubmenu.value = 2
                        }) {
                        Text(text = "Select colors")
                    }
                    Button(modifier = Modifier.fillMaxWidth(0.4f),
                        onClick = {
                            //showSubmenu.value = 3
                            navController.navigate("cards")
                        }) {
                        Text(text = "Search Card")
                    }
                }
            }

        }
    }
}

@Composable
fun subMenus(numplayers: Int) {
    Box(modifier = Modifier
        .zIndex(0.5f)
        .fillMaxSize()

    ) {
        if(colorpicker.value>0){
            colorPick()
        }
        when (showSubmenu.value) {
            1 -> { //Random player
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                        //.clip(shape = RoundedCornerShape(size = 6.dp))
                        .background(MaterialTheme.colors.background)
                        .align(Alignment.Center)
                        .scale(2f)
                    ,verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(modifier = Modifier.fillMaxWidth(0.4f),
                        onClick = {
                            rando.value = (0 until numplayers).random()
                            randoType.value = 1
                        }) {
                        Text(text = "Player")
                    }
                    Button(modifier = Modifier.fillMaxWidth(0.4f),
                        onClick = {
                            rando.value = (0 until 2).random()
                            randoType.value = 2
                        }) {
                        Text(text = "Flip Coin")
                    }
                    Button(modifier = Modifier.fillMaxWidth(0.4f),
                        onClick = {
                            rando.value = (1 until 21).random()
                            randoType.value = 3
                        }) {
                        Text(text = "D20")
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(text = "Result is:")
                    when(randoType.value){
                        1 -> {
                            Row() {
                                Text(
                                    text = "Player " + (rando.value + 1).toString(),
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.size(10.dp))
                                Box(
                                    modifier = Modifier
                                        .background(Data.colorList[Data.pColor[rando.value]])
                                        .size(20.dp, 20.dp)
                                        .align(alignment = Alignment.CenterVertically)
                                )
                            }
                        }
                        2->{
                            Row() {
                                Text(
                                    text = (if(rando.value==1) "Heads" else "Tails"),
                                    fontSize = 20.sp
                                )
                            }
                        }
                        3->{
                            Row() {
                                Text(
                                    text = (rando.value).toString(),
                                    fontSize = 20.sp
                                )
                            }
                        }
                        else -> {
                            Row() {
                                Text(
                                    text = "No result yet",
                                    fontSize = 20.sp
                                )
                            }
                        }
                    }
                }
            }
            2 -> { //Select colors
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                        .background(MaterialTheme.colors.background)
                        .align(Alignment.Center)
                        .scale(1.5f)
                    ,verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row() {
                        Text(
                            text = "Player 1 " ,
                            fontSize = 30.sp
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Button(modifier = Modifier
                            .size(30.dp, 30.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[Data.pColor[0]]),
                            onClick = {
                                colorpicker.value = 1
                        }) {
                        }
                    }
                    Row() {
                        Text(
                            text = "Player 2 " ,
                            fontSize = 30.sp
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Button(modifier = Modifier
                            .size(30.dp, 30.dp)
                            .align(alignment = Alignment.CenterVertically),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[Data.pColor[1]]),
                            onClick = {
                                colorpicker.value = 2
                            }) {
                        }
                    }
                    if(numplayers>2){
                        Row() {
                            Text(
                                text = "Player 3 " ,
                                fontSize = 30.sp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Button(modifier = Modifier
                                .size(30.dp, 30.dp)
                                .align(alignment = Alignment.CenterVertically),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[Data.pColor[2]]),
                                onClick = {
                                    colorpicker.value = 3
                                }) {
                            }
                        }
                    }
                    if(numplayers>3){
                        Row() {
                            Text(
                                text = "Player 4 " ,
                                fontSize = 30.sp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Button(modifier = Modifier
                                .size(30.dp, 30.dp)
                                .align(alignment = Alignment.CenterVertically),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[Data.pColor[3]]),
                                onClick = {
                                    colorpicker.value = 4
                                }) {
                            }
                        }
                    }
                    if(numplayers>4){
                        Row() {
                            Text(
                                text = "Player 5 " ,
                                fontSize = 30.sp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Button(modifier = Modifier
                                .size(30.dp, 30.dp)
                                .align(alignment = Alignment.CenterVertically),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[Data.pColor[4]]),
                                onClick = {
                                    colorpicker.value = 5
                                }) {
                            }
                        }
                    }
                    if(numplayers==6){
                        Row() {
                            Text(
                                text = "Player 6 " ,
                                fontSize = 30.sp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Button(modifier = Modifier
                                .size(30.dp, 30.dp)
                                .align(alignment = Alignment.CenterVertically),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[Data.pColor[5]]),
                                onClick = {
                                    colorpicker.value = 6
                                }) {
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun colorPick(){
    Box(modifier = Modifier
        .zIndex(1f)
        .fillMaxSize()
        //.clip(RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)))
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center)
            .background(MaterialTheme.colors.background), verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {
            
            Text(text = "Player "+colorpicker.value.toString()+" select a color:", fontSize = 30.sp)
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[0]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 0
                        colorpicker.value = 0
                    }) {
                }
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[1]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 1
                        colorpicker.value = 0
                    }) {
                }
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[2]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 2
                        colorpicker.value = 0
                    }) {
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[3]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 3
                        colorpicker.value = 0
                    }) {
                }
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[4]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 4
                        colorpicker.value = 0
                    }) {
                }
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[5]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 5
                        colorpicker.value = 0
                    }) {
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[6]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 6
                        colorpicker.value = 0
                    }) {
                }
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[7]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 7
                        colorpicker.value = 0
                    }) {
                }
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[8]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 8
                        colorpicker.value = 0
                    }) {
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[9]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 9
                        colorpicker.value = 0
                    }) {
                }
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[10]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 10
                        colorpicker.value = 0
                    }) {
                }
                Button(modifier = Modifier
                    .size(60.dp, 60.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Data.colorList[11]),
                    onClick = {
                        Data.pColor[colorpicker.value - 1] = 11
                        colorpicker.value = 0
                    }) {
                }
            }
            Row(modifier = Modifier.fillMaxWidth(0.7f), horizontalArrangement = Arrangement.End) {
                Button(modifier = Modifier.padding(10.dp), onClick = {
                    colorpicker.value = 0
                }) {
                    Text("Close")
                }
            }
        }
    }
}