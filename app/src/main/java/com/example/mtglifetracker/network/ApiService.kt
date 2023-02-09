package com.example.mtglifetracker.network

import com.example.mtglifetracker.model.mtgcards
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("search")
    //open fun friends(@Query("page") page: Int): Call<ResponseBody?>?
    suspend fun getCards(
        @Query("q") cname: String
    ): mtgcards
    //suspend fun getCards(): mtgcards

    companion object{
        var apiService:ApiService? = null
        fun getInstance() : ApiService{
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.scryfall.com/cards/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}