package com.example.mtglifetracker.views

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mtglifetracker.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlin.concurrent.thread

@Composable
fun players(num: Int = 2, navController: NavHostController){
    showSubmenu.value = 0
    Box {
        when(num){
            3-> {
                Three()
            }
            4-> {
                Four()
            }
            5 -> {
                Five()
            }
            6-> {
                Six()
            }
            else -> {
                Two()
            }
        }
        Button(onClick = { navController.navigate("menu/"+num.toString())
        }) {
            Text(text = "...")

        }
    }
}

@Composable
fun Two() {
    Column() {
        player(1,2, 180f)
        player(2,2)
    }
}

@Composable
fun Three() {
    Column() {
        Row(modifier = Modifier.weight(1.5f)){
            this@Column.player(1,3, 90f)
            this@Column.player(2,3,-90f)
        }

        Row(modifier = Modifier.weight(1f)){this@Column.player(3,3)}
    }
}

@Composable
fun Four() {
    Column() {
        Row(modifier = Modifier.weight(1f)){
            this@Column.player(1,4, 90f)
            this@Column.player(2,4,-90f)
        }
        Row(modifier = Modifier.weight(1f)){
            this@Column.player(3,4, 90f)
            this@Column.player(4,4,-90f)
        }

    }
}
@Composable
fun Five() {
    Column() {
        Row(modifier = Modifier.weight(1.5f)){
            this@Column.player(1,5,90f)
            this@Column.player(2,5,-90f)
        }
        Row(modifier = Modifier.weight(1.5f)){
            this@Column.player(3,5, 90f)
            this@Column.player(4,5,-90f)
        }
        Row(modifier = Modifier.weight(1f)){this@Column.player(5,5)}
    }
}
@Composable
fun Six() {
    Column() {
        Row(modifier = Modifier.weight(1f)){
            this@Column.player(1,6, 90f)
            this@Column.player(2,6,-90f)
        }
        Row(modifier = Modifier.weight(1f)){
            this@Column.player(3,6, 90f)
            this@Column.player(4,6,-90f)
        }
        Row(modifier = Modifier.weight(1f)){
            this@Column.player(5,6, 90f)
            this@Column.player(6,6,-90f)
        }
    }
}

@Composable
fun ColumnScope.player(playerid: Int, numplayers: Int, rotation:Float=0f) {
    var lifedif: MutableState<Int> = remember { mutableStateOf(0) }
    var diftimer: MutableState<Int> = remember { mutableStateOf(0) }

    fun lifedifremove() {
        while (diftimer.value>0){
            Thread.sleep(1000L)
            diftimer.value--
        }
        lifedif.value = 0
    }

    Column(modifier = Modifier
        .background(color = Data.colorList[Data.pColor[playerid - 1]])
        .fillMaxSize()
        .weight(1f)
        .rotate(rotation)
        .wrapContentWidth(unbounded = true),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

        Box(modifier = Modifier.scale(0.9f+1/numplayers.toFloat())) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .width(130.dp)
                    .align(Alignment.Center)
                    .offset(60.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                Data.lifeList[playerid - 1]++
                                lifedif.value++
                                if (diftimer.value == 0) {
                                    diftimer.value = 5
                                    thread { lifedifremove() }
                                } else {
                                    diftimer.value = 5
                                }
                            },
                            onLongPress = {
                                Data.lifeList[playerid - 1] += 10
                                lifedif.value += 10
                                if (diftimer.value == 0) {
                                    diftimer.value = 5
                                    thread { lifedifremove() }
                                } else {
                                    diftimer.value = 5
                                }
                            }
                        )
                    }
            ){}

            Text(if (lifedif.value>0) "+"+lifedif.value.toString() else lifedif.value.toString(), fontSize = if (lifedif.value==0) 0.sp else 30.sp, fontWeight = FontWeight.Normal , modifier = Modifier
                .align(Alignment.Center)
                .offset(0.dp, -60.dp))

            Text(
                Data.lifeList[playerid-1].toString(), fontFamily = FontFamily.SansSerif ,fontSize = Data.lifeList[playerid-1].scaledSp(), fontWeight = FontWeight.Normal , modifier = Modifier.align(
                    Alignment.Center))

            Box(
                modifier = Modifier
                    .height(150.dp)
                    .width(130.dp)
                    .align(Alignment.Center)
                    .offset(-60.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                Data.lifeList[playerid - 1]--
                                lifedif.value--
                                if (diftimer.value == 0) {
                                    diftimer.value = 5
                                    thread { lifedifremove() }
                                } else {
                                    diftimer.value = 5
                                }
                            },
                            onLongPress = {
                                Data.lifeList[playerid - 1] -= 10
                                lifedif.value -= 10
                                if (diftimer.value == 0) {
                                    diftimer.value = 5
                                    thread { lifedifremove() }
                                } else {
                                    diftimer.value = 5
                                }
                            }
                        )
                    }
            ){}
        }
    }
}

class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true

}

@Composable
fun Int.scaledSp(): TextUnit {
    val value: Int = this
    return with(LocalDensity.current) {
        val fontScale = this.fontScale
        val textSize = (40+value/1.35) / fontScale
        textSize.sp
    }
}