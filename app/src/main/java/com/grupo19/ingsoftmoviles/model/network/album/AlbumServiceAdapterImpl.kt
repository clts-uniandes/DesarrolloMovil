package com.grupo19.ingsoftmoviles.model.network.album

import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.model.network.RetrofitBuilder

class AlbumServiceAdapterImpl(private val clientBuilder: RetrofitBuilder = RetrofitBuilder()): AlbumServiceAdapter {

    override suspend fun getAlbums(): List<AlbumResponse> {

        //Log.i("AlbumServiceAdapterImpl", "Getting albums from back-vinyls")
        return clientBuilder.createClient(AlbumServiceRetrofit::class.java).getAlbums()
    }

}