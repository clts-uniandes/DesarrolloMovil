package com.grupo19.ingsoftmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.databinding.ActivityAsociateTrackAlbumBinding
import com.grupo19.ingsoftmoviles.databinding.ActivityMainBinding
import com.grupo19.ingsoftmoviles.model.data.TrackCreate
import com.grupo19.ingsoftmoviles.model.repo.TrackRepository
import kotlinx.coroutines.launch

class AsociateTrackAlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsociateTrackAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_asociate_track_album)

        binding = ActivityAsociateTrackAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAsociate.setOnClickListener{
            createTrack()
        }
    }
    fun createTrack(){
        val repository : TrackRepository = TrackRepository()
        val trackCreate : TrackCreate = TrackCreate("test1", "4:00")

        lifecycleScope.launch {
            val response = repository.createTrack(11, trackCreate)
            if (response){
                println("funciona")
            }else {
                println("no funciona")
            }
        }


    }
}