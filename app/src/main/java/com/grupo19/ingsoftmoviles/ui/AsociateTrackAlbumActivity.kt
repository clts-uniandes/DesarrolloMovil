package com.grupo19.ingsoftmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
            val id_album = 11
            var validateName: Boolean = false
            var validateDuration: Boolean = false

            if(binding.trackName.text.toString().isEmpty()) {
                binding.trackNameLy.setError("Campo requerido")
                validateName = false
            }else{
                binding.trackNameLy.setError(null)
                validateName = true
            }
            if(binding.trackDuration.text.toString().isEmpty()) {
                binding.trackDurationLy.setError("Campo requerido")
                validateDuration = false
            }else{
                validateDuration = true
                binding.trackDurationLy.setError(null)
            }
            if(validateName && validateDuration){
                createTrack(id_album, binding.trackName.text.toString(), binding.trackDuration.text.toString())
            }
        }
        trackViewModel.statusMessage.observe(this){
            if(it){
                showAlert("Track Asociado", "El track ha sido asociado correctamente al album. Ahora puede verlo en el listado.")
                Toast.makeText(this, "Track Creado", Toast.LENGTH_LONG).show()
            }else{
                showAlert("Error", "Ocurrio un error asociando el track al album.")
                Toast.makeText(this, "Ha ocurrido un error, intente nuevamente", Toast.LENGTH_LONG).show()
            }


        }
    }
    fun createTrack(id_album:Int, name_track:String, duration_track:String){
        val repository : TrackRepository = TrackRepository()
        val trackCreate : TrackCreate = TrackCreate(name_track, duration_track)
        trackViewModel.onCreateTrack(11, trackCreate)
    }
    fun showAlert(title:String,message:String){
        val buttonCancel = getString(R.string.button_cancel_pupup_track)
        val buttonOk = getString(R.string.button_ok_pupup_track)
        MaterialAlertDialogBuilder(this@AsociateTrackAlbumActivity)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(buttonCancel) { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton(buttonOk) { dialog, which ->
                // Respond to positive button press
            }
            .show()
    }
}