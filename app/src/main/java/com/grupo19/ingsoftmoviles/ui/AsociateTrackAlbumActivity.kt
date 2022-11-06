package com.grupo19.ingsoftmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.databinding.ActivityAsociateTrackAlbumBinding
import com.grupo19.ingsoftmoviles.databinding.ActivityMainBinding
import com.grupo19.ingsoftmoviles.model.data.TrackCreate
import com.grupo19.ingsoftmoviles.model.repo.TrackRepository
import com.grupo19.ingsoftmoviles.viewmodel.TrackViewModel
import kotlinx.coroutines.launch

class AsociateTrackAlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsociateTrackAlbumBinding

    private val trackViewModel by viewModels<TrackViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asociate_track_album)

        binding = ActivityAsociateTrackAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonAsociate.setOnClickListener{
            createTrack()
        }
        trackViewModel.statusMessage.observe(this){
            if(it){
                Toast.makeText(this, "Track Creado", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "Ha ocurrido un error, intente nuevamente", Toast.LENGTH_LONG).show()
            }


        }
    }
    fun createTrack(){
        val repository : TrackRepository = TrackRepository()
        val trackCreate : TrackCreate = TrackCreate("test1", "4:00")
        trackViewModel.onCreateTrack(11, trackCreate)
    }
}