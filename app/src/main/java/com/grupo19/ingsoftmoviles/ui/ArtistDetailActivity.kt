package com.grupo19.ingsoftmoviles.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.databinding.ActivityArtistDetailBinding
import com.grupo19.ingsoftmoviles.model.data.ArtistDetailAlbumListWrapper
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse

class ArtistDetailActivity: AppCompatActivity()  {

    private lateinit var binding: ActivityArtistDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val artis: ArtistResponse = intent.getSerializableExtra(Constants.ARTIST_OBJECT) as ArtistResponse
        println(Gson().toJson(artis))

        setHeader(artis.name)
        setImage(artis.image)
        setBody(artis.name, artis.birthDate, artis.description)
        setActionForButtons(artis.albums)
    }

    private fun setHeader(name: String) {
        binding.cardArtistDetailPrimaryTitle.text = name
    }

    private fun setImage(imageUrl: String) {
        Glide.with(this)
            .load(imageUrl.toUri().buildUpon().scheme("https").build())
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(R.drawable.ic_broken_image))
            .into(binding.cardArtistDetailImage)
    }

    private fun setBody(name:String, birthDate: String, description: String) {
        binding.cardArtistDetailSecondaryTitle.text = name
        binding.cardArtistDetailSecondaryDescription.text = birthDate
        binding.cardArtistDetailSecondarySupportingText.text = description
    }

    private fun setActionForButtons(albums: List<ArtistResponse.AlbumResponse>) {
        binding.artistDetailAlbumButton.setOnClickListener { showArtistDetailAlbumList(albums) }
    }

    private fun showArtistDetailAlbumList(albums: List<ArtistResponse.AlbumResponse>) {
        val intent = Intent(this, ArtistDetailAlbumListActivity::class.java).apply {
            putExtra(Constants.ALBUMS_LIST, ArtistDetailAlbumListWrapper(albums))
        }
        startActivity(intent)
    }

}