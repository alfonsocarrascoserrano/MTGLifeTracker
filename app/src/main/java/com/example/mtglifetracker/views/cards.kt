package com.example.mtglifetracker.views

import android.annotation.SuppressLint
import android.text.Layout.Alignment
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mtglifetracker.model.MTGCard
import com.example.mtglifetracker.viewModel.CardViewModel
import com.example.mtglifetracker.views.*
import coil.compose.rememberImagePainter
import coil.size.Scale

val cBrowse = mutableStateOf(false)
var name = mutableStateOf("")

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun cards(navController: NavController, cardViewModel: CardViewModel) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
                    }
                },
                title = {
                    TextField(
                        value = name.value,
                        onValueChange = { name.value = it },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                focusManager.clearFocus()
                                cardBrowse(cardViewModel)

                            }),
                        label = { Text("Search cards") }
                    )
                },
                actions = {
                    IconButton(onClick = {
                        focusManager.clearFocus()
                        cardBrowse(cardViewModel)
                        }) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    }
                }
            )
        }
    ) {
        if (cardViewModel.cardListResponse.size==0) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(text = "No results",fontSize= 30.sp)
                }
            }
        } else {
            CardList(cardList = cardViewModel.cardListResponse)
        }
    }
}

fun cardBrowse(cardViewModel: CardViewModel){
    cardViewModel.getCardList(name.value)
}

@Composable
fun CardList(cardList: List<MTGCard>){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .zIndex(1f)) {
        Text("Result: "+cardList.size.toString()+" cards")
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){
            itemsIndexed(items= cardList){index, item ->
                CardItem(card = item)
            }
        }
    }
}

@Composable
fun CardItem( card: MTGCard) { //navController: NavHostController,
    Column(modifier = Modifier
        .padding(8.dp, 4.dp)
        .fillMaxWidth()
        .wrapContentHeight()//.height(500.dp)
        .clip(RoundedCornerShape(2))
        .background(Color.DarkGray)
    ) {
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min))
            {
                if(card.image_uris!=null){
                    Image(
                        painter = rememberImagePainter
                            (data = card.image_uris.normal,
                            builder = {
                                scale(Scale.FILL)
                            }
                        ),
                        contentDescription = card.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .height(440.dp)
                    )
                } else{
                    if(card.card_faces[0]!=null){
                    Image(
                        painter = rememberImagePainter
                            (data = card.card_faces[0].image_uris.normal,
                            builder = {
                                scale(Scale.FILL)
                            }
                        ),
                        contentDescription = card.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .height(440.dp)
                    )}
                    if(card.card_faces[1]!=null){
                    Spacer(modifier = Modifier.height(5.dp))
                    Image(
                        painter = rememberImagePainter
                            (data = card.card_faces[1].image_uris.normal,
                            builder = {
                                scale(Scale.FILL)
                            }
                        ),
                        contentDescription = card.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .height(440.dp)
                    )}
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    //modifier = Modifier
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        if (card.name != null) {
                            Text(
                                text = card.name,
                                //modifier = Modifier
                            )
                        }
                        if (card.prices.usd != null) {
                            Text(
                                text = "$"+card.prices.usd,
                                //modifier = Modifier
                            )
                        } else {Text(
                            text = "$...",
                            //modifier = Modifier
                        )}
                    }
                }
        }
    }
}