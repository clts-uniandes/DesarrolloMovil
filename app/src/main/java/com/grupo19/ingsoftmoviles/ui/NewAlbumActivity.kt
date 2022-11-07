package com.grupo19.ingsoftmoviles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grupo19.ingsoftmoviles.databinding.ActivityNewAlbumBinding

import android.view.View
import android.widget.*
import androidx.activity.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.model.ResultWrapper
import com.grupo19.ingsoftmoviles.viewmodel.NewAlbumViewModel

class NewAlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewAlbumBinding
    private val newAlbumViewModel by viewModels<NewAlbumViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        val genres = resources.getStringArray(R.array.Genres)
        val recordLabels = resources.getStringArray(R.array.RecordLabels)

        var selectedGenre : String = genres[0]
        var selectedRecordLabel : String = recordLabels[0]

        super.onCreate(savedInstanceState)
        binding = ActivityNewAlbumBinding.inflate(layoutInflater)
        //setContentView(R.layout.new_album_item)
        setContentView(binding.root)

        /*val genreAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genres)
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val genreSpinner = findViewById<Spinner>(R.id.spinnerGenre)
        genreSpinner.adapter = genreAdapter
        //genreSpinner.setSelection(1, false)
        genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedGenre = genres[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }*/

        /*val recordLabelAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, recordLabels)
        recordLabelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val recordLabelSpinner = findViewById<Spinner>(R.id.spinnerRecordLabel)
        recordLabelSpinner.adapter = recordLabelAdapter
        //recordLabelSpinner.setSelection(0, false)
        recordLabelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedRecordLabel = recordLabels[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }*/

        val genreDropdownAdapter = ArrayAdapter(this,R.layout.material_list_item, genres)
        val genreDropdown = findViewById<AutoCompleteTextView>(R.id.spinnerGenre)
        genreDropdown.setAdapter(genreDropdownAdapter)
        genreDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedGenre = genres[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val labelDropdownAdapter = ArrayAdapter(this,R.layout.material_list_item, genres)
        val recordLabelDropdown = findViewById<AutoCompleteTextView>(R.id.spinnerRecordLabel)
        recordLabelDropdown.setAdapter(labelDropdownAdapter)
        recordLabelDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedRecordLabel = recordLabels[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val createButton: Button = findViewById(R.id.buttonCreateAlbum)
        createButton.setOnClickListener {
            val name = findViewById<EditText?>(R.id.editTextName).text.toString()
            val cover = findViewById<EditText?>(R.id.editTextCoverUrl).text.toString()
            val releaseDate = findViewById<EditText?>(R.id.editTextDate).text.toString()
            val description = findViewById<EditText?>(R.id.editTextDescription).text.toString()
            val genre = selectedGenre
            val recordLabel = selectedRecordLabel
            newAlbumViewModel.createAlbum(name = name, cover = cover, releaseDate = releaseDate,
                description = description, genre = genre, recordLabel = recordLabel)

            showAlert("Album guardado", "El album ha sido almacenado con exito, ahora puede verlo en la lista de albumes o asociar canciones a este.")

            //showToast("Called")
        }

        newAlbumViewModel.creationResult.observe(this) {
            when (it) {
                is ResultWrapper.Loading -> {  }
                is ResultWrapper.Success -> {
                    showToast("Success")
                }
                is ResultWrapper.Error -> {
                    showToast("Error al crear album")
                }
            }
        }

    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun showAlert(title:String,message:String){
        val buttonCancel = getString(R.string.button_cancel_pupup_album)
        val buttonOk = getString(R.string.button_ok_pupup_album)
        MaterialAlertDialogBuilder(this@NewAlbumActivity)
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