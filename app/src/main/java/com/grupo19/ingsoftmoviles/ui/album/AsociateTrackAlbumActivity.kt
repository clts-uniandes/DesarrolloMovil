package com.grupo19.ingsoftmoviles.ui.album

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.LocalContext
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.databinding.ActivityAsociateTrackAlbumBinding
import com.grupo19.ingsoftmoviles.model.data.TrackCreate
import com.grupo19.ingsoftmoviles.model.repo.TrackRepository
import com.grupo19.ingsoftmoviles.ui.Constants
import com.grupo19.ingsoftmoviles.ui.Constants.ALBUM_ID_ERROR
import com.grupo19.ingsoftmoviles.ui.HomeActivity
import com.grupo19.ingsoftmoviles.viewmodel.TrackViewModel

class AsociateTrackAlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsociateTrackAlbumBinding

    private val trackViewModel by viewModels<TrackViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asociate_track_album)

        binding = ActivityAsociateTrackAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumId: Int = intent.extras?.getInt(Constants.ALBUM_ID)?.toInt() ?: ALBUM_ID_ERROR

        binding.buttonAsociate.setOnClickListener{
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
                createTrack(albumId, binding.trackName.text.toString(), binding.trackDuration.text.toString())
            }
        }
        trackViewModel.statusMessage.observe(this){
            if(it){
                showAlert("Track Asociado", "El track ha sido asociado correctamente al album. Ahora puede verlo en el listado.", albumId)
                Toast.makeText(this, "Track Creado", Toast.LENGTH_LONG).show()
            }else{
                showAlert("Error", "Ocurrio un error asociando el track al album.", albumId)
                Toast.makeText(this, "Ha ocurrido un error, intente nuevamente", Toast.LENGTH_LONG).show()
            }


        }
    }
    fun createTrack(albumId:Int, name_track:String, duration_track:String){
        val trackCreate : TrackCreate = TrackCreate(name_track, duration_track)
        trackViewModel.onCreateTrack(albumId, trackCreate)
    }
    fun showAlert(title:String,message:String, albumId:Int){
        val buttonCancel = getString(R.string.button_cancel_pupup_track)
        val buttonOk = getString(R.string.button_ok_pupup_track)
        val context = this@AsociateTrackAlbumActivity
        MaterialAlertDialogBuilder(context)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(buttonCancel) { dialog, which ->
                // Respond to negative button press
                val intent = Intent(context, HomeActivity::class.java)
                context.startActivity(intent)
            }
            .setPositiveButton(buttonOk) { dialog, which ->
                // Respond to positive button press
                val intent = Intent(context, HomeActivity::class.java)
                context.startActivity(intent)
            }
            .show()
    }
}