package com.example.mtglifetracker.views

import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
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

@Composable
fun cards(navController: NavController, cardViewModel: CardViewModel){
    val focusManager = LocalFocusManager.current

    if(cBrowse.value){
        cardBrowse(cardViewModel)
    }

    Column() {
        Text(text = "Search cards:")

        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Card name") }
        )

        Button(modifier = Modifier.padding(10.dp), onClick = {
            focusManager.clearFocus()
            cBrowse.value=true
        }) {
            Text("Search")
        }
        Button(modifier = Modifier.padding(10.dp), onClick = {
            navController.popBackStack()
        }) {
            Text("Close")
        }
    }
}

@Composable
fun cardBrowse(cardViewModel: CardViewModel){
    CardList(cardList = cardViewModel.cardListResponse)
    cardViewModel.getCardList(name.value)
}

@Composable
fun CardList(cardList: List<MTGCard>){
    Column(modifier = Modifier.fillMaxSize().background(Color.White).zIndex(1f)) {
        Text("Result: "+cardList.size.toString()+" cards")
        Button(modifier = Modifier.padding(10.dp), onClick = {
            cBrowse.value=false
        }) {
            Text("Close")
        }
        LazyColumn{
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
        .background(Color.White)
        //, shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface(){
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min))
            {
                if(card.card_faces== null){
                    Image(
                        painter = rememberImagePainter
                            (data = card.image_uris.normal,
                            builder = {
                                scale(Scale.FILL)
                                //placeholder(R.drawable.notification_action_background)
                                //transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = card.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .height(440.dp)
                    )
                } else{
                    Image(
                        painter = rememberImagePainter
                            (data = card.card_faces[0].image_uris.normal,
                            builder = {
                                scale(Scale.FILL)
                                //placeholder(R.drawable.notification_action_background)
                                //transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = card.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .height(440.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Image(
                        painter = rememberImagePainter
                            (data = card.card_faces[1].image_uris.normal,
                            builder = {
                                scale(Scale.FILL)
                                //placeholder(R.drawable.notification_action_background)
                                //transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = card.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .height(440.dp)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        //.padding(4.dp)
                        //.fillMaxHeight()
                        //.weight(1f)
                ) {
                    Text(text = card.name,
                        modifier = Modifier
                            .background(Color.White)
                            //.padding(4.dp)
                    )
                }
            }
        }
    }
}