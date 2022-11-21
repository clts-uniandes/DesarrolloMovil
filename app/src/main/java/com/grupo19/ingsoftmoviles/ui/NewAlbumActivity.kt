package com.grupo19.ingsoftmoviles.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.databinding.ActivityNewAlbumBinding
import com.grupo19.ingsoftmoviles.model.ResultWrapper
import com.grupo19.ingsoftmoviles.viewmodel.NewAlbumViewModel
import java.text.SimpleDateFormat
import java.util.*

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

        val dateTextBox = findViewById<TextView>(R.id.editTextDate)

        dateTextBox.setOnClickListener { // getSupportFragmentManager() to
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
            datePicker.show(supportFragmentManager, "Escoja fecha")
            datePicker.addOnPositiveButtonClickListener {
                val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
                val date = dateFormatter.format(Date(it))
                dateTextBox.text = date
            }
        }

        val genreDropdownAdapter = ArrayAdapter(this,R.layout.material_list_item, genres)
        val genreDropdown = findViewById<AutoCompleteTextView>(R.id.spinnerGenre)
        genreDropdown.setAdapter(genreDropdownAdapter)
        genreDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedGenre = genres[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val labelDropdownAdapter = ArrayAdapter(this,R.layout.material_list_item, recordLabels)
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
            val releaseDate = dateTextBox.text.toString()
            val description = findViewById<EditText?>(R.id.editTextDescription).text.toString()
            val genre = selectedGenre
            val recordLabel = selectedRecordLabel
            newAlbumViewModel.createAlbum(name = name, cover = cover, releaseDate = releaseDate,
                description = description, genre = genre, recordLabel = recordLabel)


            //showToast("Called")
        }

        newAlbumViewModel.creationResult.observe(this) {
            when (it) {
                is ResultWrapper.Loading -> {  }
                is ResultWrapper.Success -> {
                    showAlert("Album guardado", "El album ha sido almacenado con exito, ahora puede verlo en la lista de albumes o asociar canciones a este.")
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