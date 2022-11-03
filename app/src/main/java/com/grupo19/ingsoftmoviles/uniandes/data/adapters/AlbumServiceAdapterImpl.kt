package com.grupo19.ingsoftmoviles.uniandes.data.adapters

import android.util.Log
import com.grupo19.ingsoftmoviles.uniandes.data.AlbumResponse

class AlbumServiceAdapterImpl: AlbumServiceAdapter {

    override suspend fun getAlbums(): List<AlbumResponse> {

        Log.i("AlbumServiceAdapterImpl", "Getting albums from back-vinyls")

        return AlbumClient.service.getAlbums()
    }

}