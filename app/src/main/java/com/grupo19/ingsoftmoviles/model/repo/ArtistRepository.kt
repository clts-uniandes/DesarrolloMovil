package com.grupo19.ingsoftmoviles.model.repo

import android.content.Context
import android.net.ConnectivityManager
import com.grupo19.ingsoftmoviles.database.dao.ArtistsResponse
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import com.grupo19.ingsoftmoviles.model.network.artist.ArtistServiceAdapter
import com.grupo19.ingsoftmoviles.model.network.artist.ArtistServiceAdapterImpl
import com.grupo19.ingsoftmoviles.ui.ArtistsListActivity
import com.grupo19.ingsoftmoviles.ui.adapters.ArtistAdapter

class ArtistRepository(private val artistServiceAdapter: ArtistsListActivity, private val application: ArtistsListActivity, private val artistsDao: ArtistsResponse) {
    suspend fun getArtists(): List<ArtistResponse> {
        var cahed = artistsDao.getArtists()
        return if(cahed.isNullOrEmpty()){
            val ar = application.baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if(ar.activeNetworkInfo?.type != ConnectivityManager.TYPE_WIFI && ar.activeNetworkInfo?.type != ConnectivityManager.TYPE_MOBILE){
                emptyList()
            }else artistServiceAdapter.getArtists()
        }else cahed

    }
}