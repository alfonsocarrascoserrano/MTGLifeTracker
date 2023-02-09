package com.example.mtglifetracker.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mtglifetracker.model.MTGCard
import com.example.mtglifetracker.model.mtgcards
import com.example.mtglifetracker.network.ApiService
import kotlinx.coroutines.launch

class CardViewModel: ViewModel() {
    var cardListResponse:List<MTGCard> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getCardList(name: String){
        viewModelScope.launch{
            val apiService = ApiService.getInstance()
            try {
                val cardList = apiService.getCards(name)
                cardListResponse = cardList.data
            }
            catch(e:Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}