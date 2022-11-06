package com.grupo19.ingsoftmoviles.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.grupo19.ingsoftmoviles.R

class CreateAlbum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_album)

        val postButton: Button = findViewById(R.id.button_create)
        val name : TextInputEditText = findViewById(R.id.album_name)
        val cover : TextInputEditText = findViewById(R.id.album_cover)
        val date : TextInputEditText = findViewById(R.id.album_date)
        val description : TextInputEditText = findViewById(R.id.album_description)
        val genre : AutoCompleteTextView = findViewById(R.id.spinner_genre)

        val recordlabel : AutoCompleteTextView = findViewById(R.id.spinner_recordlabel)
        val genres = resources.getStringArray(R.array.genre_array)
        val records  = resources.getStringArray(R.array.recordlabel_array)
        val adapterGenres = ArrayAdapter(this, android.R.layout.simple_list_item_1, genres)
        genre.setAdapter(adapterGenres)
        val adapterRecords = ArrayAdapter(this, android.R.layout.simple_list_item_1, records)
        recordlabel.setAdapter(adapterRecords)
        fun validateForm(){
            val errorElement : TextInputLayout = findViewById(R.id.album_name_ly)
            val validate : Boolean = name.text.toString().isEmpty()
            if (validate){
                errorElement.setError("Campo requerido")
            }
            else{
                errorElement.setError(null)
            }
            MaterialAlertDialogBuilder(this@CreateAlbum)
                .setTitle("text")
                .setMessage("text")
                .setNeutralButton("text") { dialog, which ->
                    // Respond to neutral button press
                }
                .setNegativeButton("text") { dialog, which ->
                    // Respond to negative button press
                }
                .setPositiveButton("text") { dialog, which ->
                    // Respond to positive button press
                }
                .show()

        }
        postButton.setOnClickListener {
            validateForm()
            val postParams = mapOf<String, Any>(
                "name" to name.text.toString(),
                "cover" to cover.text.toString(),
                "date" to date.text.toString(),
                "description" to description.text.toString(),
                "genre" to genre.getText().toString(),
                "recordlabel" to recordlabel.getText().toString(),

                )
        validateForm()
            Log.d("test1", postParams.toString())
        }
    }
}