package com.grupo19.ingsoftmoviles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grupo19.ingsoftmoviles.databinding.NewAlbumItemBinding

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.viewModels
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.model.ResultWrapper
import com.grupo19.ingsoftmoviles.viewmodel.NewAlbumViewModel

class NewAlbumActivity : AppCompatActivity() {

    private lateinit var binding: NewAlbumItemBinding
    private val newAlbumViewModel by viewModels<NewAlbumViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        val genres = resources.getStringArray(R.array.Genres)
        val recordLabels = resources.getStringArray(R.array.RecordLabels)

        var selectedGenre : String = genres[0]
        var selectedRecordLabel : String = recordLabels[0]

        super.onCreate(savedInstanceState)
        binding = NewAlbumItemBinding.inflate(layoutInflater)
        //setContentView(R.layout.new_album_item)
        setContentView(binding.root)

        val genreAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genres)
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val genreSpinner = findViewById<Spinner>(R.id.spinnerGenre)
        genreSpinner.adapter = genreAdapter
        //genreSpinner.setSelection(1, false)
        genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedGenre = genres[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        val recordLabelAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, recordLabels)
        recordLabelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        val recordLabelSpinner = findViewById<Spinner>(R.id.spinnerRecordLabel)
        recordLabelSpinner.adapter = recordLabelAdapter
        //recordLabelSpinner.setSelection(0, false)
        recordLabelSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedRecordLabel = recordLabels[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) { }
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

    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}