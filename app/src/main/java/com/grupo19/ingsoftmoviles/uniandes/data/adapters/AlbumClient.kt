package com.grupo19.ingsoftmoviles.uniandes.data.adapters

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AlbumClient {

    private const val BASE_URL = "https://isam-grupo19-vynils-back.herokuapp.com"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val service: AlbumServiceRetrofit = getRetrofit().create(AlbumServiceRetrofit::class.java)
}