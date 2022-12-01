package com.grupo19.ingsoftmoviles.ui.album

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.databinding.ActivityNewAlbumBinding
import com.grupo19.ingsoftmoviles.model.ResultWrapper
import com.grupo19.ingsoftmoviles.ui.Constants
import com.grupo19.ingsoftmoviles.ui.HomeActivity
import com.grupo19.ingsoftmoviles.viewmodel.NewAlbumViewModel
import java.text.SimpleDateFormat
import java.util.*

class NewAlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewAlbumBinding
    private val newAlbumViewModel by viewModels<NewAlbumViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        val genres = resources.getStringArray(R.array.Genres)
        val recordLabels = resources.getStringArray(R.array.RecordLabels)

        super.onCreate(savedInstanceState)
        binding = ActivityNewAlbumBinding.inflate(layoutInflater)
        //setContentView(R.layout.new_album_item)
        setContentView(binding.root)

        val dateTextBox = findViewById<TextView>(R.id.editTextDate)

        dateTextBox.setOnClickListener { // getSupportFragmentManager() to
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            datePicker.show(supportFragmentManager, "Escoja fecha")
            datePicker.addOnPositiveButtonClickListener {
                val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
                val cal = Calendar.getInstance()
                cal.time = Date(it)
                cal.add(Calendar.DATE, 1)
                dateTextBox.text = dateFormatter.format(cal.time)
            }
        }

        val genreDropdownAdapter = ArrayAdapter(this,R.layout.material_list_item, genres)
        val genreDropdown = binding.spinnerGenre
        genreDropdown.setAdapter(genreDropdownAdapter)

        val labelDropdownAdapter = ArrayAdapter(this,R.layout.material_list_item, recordLabels)
        val recordLabelDropdown = findViewById<AutoCompleteTextView>(R.id.spinnerRecordLabel)
        recordLabelDropdown.setAdapter(labelDropdownAdapter)

        val createButton: Button = findViewById(R.id.buttonCreateAlbum)
        createButton.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val cover = binding.editTextCoverUrl.text.toString()
            val releaseDate = dateTextBox.text.toString()
            val description = binding.editTextDescription.text.toString()
            val genre = binding.spinnerGenre.text.toString()
            val recordLabel = binding.spinnerRecordLabel.text.toString()
            if(validateData(name, cover, releaseDate, description, genre, recordLabel, binding)){
                newAlbumViewModel.createAlbum(name = name, cover = cover, releaseDate = releaseDate,
                    description = description, genre = genre, recordLabel = recordLabel)
            }
        }

        newAlbumViewModel.creationResult.observe(this) {
            when (it) {
                is ResultWrapper.Loading -> {  }
                is ResultWrapper.Success -> {
                    it.data?.id?.let { it1 ->
                        showAlert("Album guardado", "El album ha sido almacenado con exito, ahora puede verlo en la lista de albumes o asociar canciones a este.", this,
                            it1)
                    }
                }
                is ResultWrapper.Error -> {
                    showToast("Error al crear album")
                }
            }
        }

    }
    private fun validateData(name:String, cover:String, releaseDate:String, description:String, genre:String, recodLabel:String, binding: ActivityNewAlbumBinding): Boolean {
        var validateName = false
        var validateCover = false
        var validateReleaseDate = false
        var validateDescription = false
        var validateGenre = false
        var validateRecordLabel = false
        if(name.isEmpty()) {
            binding.nameLy.error = "Campo requerido"
            validateName = false
        }else{
            binding.nameLy.error = null
            validateName = true
        }
        if(cover.isEmpty()) {
            binding.CoverUrlLy.error = "Campo requerido"
            validateCover = false
        }else{
            validateCover = true
            binding.CoverUrlLy.error = null
        }
        if(releaseDate.isEmpty()) {
            binding.dateLy.error = "Campo requerido"
            validateReleaseDate = false
        }else{
            validateReleaseDate = true
            binding.dateLy.error = null
        }
        if(description.isEmpty()) {
            binding.descriptionLy.error = "Campo requerido"
            validateDescription = false
        }else{
            validateDescription = true
            binding.descriptionLy.error = null
        }
        if(genre.isEmpty()) {
            binding.genreLy.error = "Campo requerido"
            validateGenre = false
        }else{
            validateGenre = true
            binding.genreLy.error = null
        }
        if(recodLabel.isEmpty()) {
            binding.recordLy.error = "Campo requerido"
            validateRecordLabel = false
        }else{
            validateRecordLabel = true
            binding.recordLy.error = null
        }
        if(validateName && validateCover && validateReleaseDate && validateDescription && validateGenre && validateRecordLabel){
            return true
        }
        return false

    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showAlert(title:String, message:String, context:Context, albumId:Int){
        val buttonCancel = getString(R.string.button_cancel_pupup_album)
        val buttonOk = getString(R.string.button_ok_pupup_album)
        MaterialAlertDialogBuilder(this@NewAlbumActivity)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton(buttonCancel) { dialog, which ->
                loadAlbumList(context)
            }
            .setPositiveButton(buttonOk) { dialog, which ->
                loadAssociateTrack(context, albumId)
            }
            .show()
    }
    private fun loadAssociateTrack(context: Context, albumId:Int){
        val intent = Intent(context, AsociateTrackAlbumActivity::class.java)
        intent.putExtra(Constants.ALBUM_ID, albumId)
        context.startActivity(intent)
    }
    private fun loadAlbumList(context: Context){
        val intent = Intent(context, HomeActivity::class.java)
        context.startActivity(intent)
    }
}