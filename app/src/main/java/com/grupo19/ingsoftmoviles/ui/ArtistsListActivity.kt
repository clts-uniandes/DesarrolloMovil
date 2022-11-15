package com.grupo19.ingsoftmoviles.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.grupo19.ingsoftmoviles.databinding.ActivityArtistsListBinding
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import com.grupo19.ingsoftmoviles.ui.adapters.ArtistAdapter
import com.grupo19.ingsoftmoviles.viewmodel.ArtistViewModel

class ArtistsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistsListBinding
    private val artistViewModel by viewModels<ArtistViewModel>()
    private lateinit var viewModel: ArtistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ArtistViewModel.Factory(this)).get(ArtistViewModel::class.java)
        binding = ActivityArtistsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        artistViewModel.artists.observe(this){
            artists -> binding.artistRecyclerView.adapter = ArtistAdapter(this, artists)
        }
        artistViewModel.progressVisible.observe(this) {
            binding.artistProgress.visibility = if (it) View.VISIBLE else View.GONE
        }

        artistViewModel.onCreate()


    }
}