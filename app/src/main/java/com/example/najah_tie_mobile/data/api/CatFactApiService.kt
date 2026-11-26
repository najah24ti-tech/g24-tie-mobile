package com.example.najah_tie_mobile.data.api

import com.example.najah_tie_mobile.data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("fact")
    suspend fun getCatFact(): CatFactModel
}