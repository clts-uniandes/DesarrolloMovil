package com.grupo19.ingsoftmoviles.model.network.album

import android.util.Log
import com.grupo19.ingsoftmoviles.model.data.Album
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse

class AlbumServiceAdapterImpl: AlbumServiceAdapter {

    override suspend fun getAlbums(): List<AlbumResponse> {

        Log.i("AlbumServiceAdapterImpl", "Getting albums from back-vinyls")

        return listOf(AlbumResponse(1, "album1", "cover1"), AlbumResponse(2, "album2", "cover2"))
    }

}