package com.grupo19.ingsoftmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.databinding.ActivityMainBinding
import com.grupo19.ingsoftmoviles.ui.adapters.AlbumAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val albumViewModel by viewModels<AlbumViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        albumViewModel.albums.observe(this) {
                albums -> binding.albumsRecyclerView.adapter = AlbumAdapter(this, albums)
        }

        albumViewModel.progressVisible.observe(this) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        albumViewModel.showMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

        albumViewModel.onCreate()
    }
}