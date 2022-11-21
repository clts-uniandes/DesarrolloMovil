package com.grupo19.ingsoftmoviles.ui.artist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.grupo19.ingsoftmoviles.databinding.ActivityArtistDetailAlbumListBinding
import com.grupo19.ingsoftmoviles.model.data.ArtistDetailAlbumListWrapper
import com.grupo19.ingsoftmoviles.ui.Constants
import com.grupo19.ingsoftmoviles.ui.adapters.ArtistDetailAlbumAdapter

class ArtistDetailAlbumListActivity: AppCompatActivity()  {

    private lateinit var binding: ActivityArtistDetailAlbumListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistDetailAlbumListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumsWrapper: ArtistDetailAlbumListWrapper = intent.getSerializableExtra(Constants.ALBUMS_LIST) as ArtistDetailAlbumListWrapper
        println(Gson().toJson(albumsWrapper.albums))

        binding.artistDetailAlbumRecyclerView.adapter = ArtistDetailAlbumAdapter(this, albumsWrapper.albums)
    }
}