package com.example.najah_tie_mobile.Home.data.api

import com.example.najah_tie_mobile.Home.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}