package com.grupo19.ingsoftmoviles.ui.artist

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.databinding.ActivityArtistDetailBinding
import com.grupo19.ingsoftmoviles.model.data.ArtistDetailAlbumListWrapper
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import com.grupo19.ingsoftmoviles.ui.Constants
import com.grupo19.ingsoftmoviles.viewmodel.ArtistDetailViewModel

class ArtistDetailActivity: AppCompatActivity()  {

    private lateinit var binding: ActivityArtistDetailBinding

    private val artistDetailViewModel by viewModels<ArtistDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val artistId: Int = intent.getIntExtra(Constants.ARTIST_ID, Constants.ARTIST_ID_ERROR)
        println("Artista ID: $artistId")

        artistDetailViewModel.onCreate(artistId)

        artistDetailViewModel.artist.observe(this){
                populateLayout(it)
        }
        artistDetailViewModel.progressVisible.observe(this) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

    }

    private fun populateLayout(artist: ArtistResponse) {
        setHeader(artist.name)
        setImage(artist.image)
        setBody(artist.name, artist.birthDate, artist.description)
        setActionForButtons(artist.albums)
    }

    private fun setHeader(name: String) {
        binding.cardArtistDetailPrimaryTitle.text = name
    }

    private fun setImage(imageUrl: String) {
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){
            Glide.with(this)
                .load(imageUrl)
                .into(binding.cardArtistDetailImage)
        }else{
            Glide.with(this)
                .load(imageUrl.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image))
                .into(binding.cardArtistDetailImage)
        }
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