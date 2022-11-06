package com.grupo19.ingsoftmoviles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grupo19.ingsoftmoviles.databinding.NewAlbumItemBinding

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.grupo19.ingsoftmoviles.R

class NewAlbumActivity : AppCompatActivity() {

    private lateinit var binding: NewAlbumItemBinding

    val NEW_SPINNER_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewAlbumItemBinding.inflate(layoutInflater)
        //setContentView(R.layout.new_album_item)
        setContentView(binding.root)

        val genres = resources.getStringArray(R.array.Genres)
        val recordLabels = resources.getStringArray(R.array.RecordLabels)

        val genreSpinner = findViewById<Spinner>(R.id.spinnerGenre)
        if (genreSpinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genres)
            //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            genreSpinner.adapter = adapter
            genreSpinner.setSelection(0, false)

            genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@NewAlbumActivity, "Selected item: " + genres[position], Toast.LENGTH_SHORT).show()// solo para popup toast
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //TODO("Acciones requeridas si no hay seleccion hecha")
                }
            }
        }

        val recordLabelSpinner = findViewById<Spinner>(R.id.spinnerRecordLabel)
        if (recordLabelSpinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, recordLabels)
            //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            genreSpinner.adapter = adapter
            genreSpinner.setSelection(0, false)

            genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(this@NewAlbumActivity, "Selected item: " + recordLabels[position], Toast.LENGTH_SHORT).show()// solo para popup toast
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //TODO("Acciones requeridas si no hay seleccion hecha")
                }
            }
        }


    }

}