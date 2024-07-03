package com.example.alextimofeev_android_hw14

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://randomuser.me/"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val randomGenerateServiceApiResponse: RandomGenerateServiceApiResponse = retrofit.create(
        RandomGenerateServiceApiResponse::class.java
    )
}

interface RandomGenerateServiceApiResponse{
    @GET("api/")
    suspend fun getPersonProfile(): Response<PersonProfile>
}