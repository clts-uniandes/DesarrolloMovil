package com.grupo19.ingsoftmoviles.model.network.album

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    private val BASE_URL = "https://isam-grupo19-vynils-back.herokuapp.com"

    fun<T> createClient(clazz: Class<T>, baseUrl: String = BASE_URL): T {
        val retrofit =  Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(clazz)
    }

}