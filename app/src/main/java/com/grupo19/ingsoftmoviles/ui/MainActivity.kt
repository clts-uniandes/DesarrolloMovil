package com.grupo19.ingsoftmoviles.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.grupo19.ingsoftmoviles.databinding.ActivityMainBinding
import com.grupo19.ingsoftmoviles.ui.adapters.AlbumAdapter
import com.grupo19.ingsoftmoviles.viewmodel.AlbumViewModel

const val EXTRA_MESSAGE = "com.grupo19.ingsoftmoviles.MESSAGE"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val albumViewModel by viewModels<AlbumViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        albumViewModel.albums.observe(this) { albums ->
            binding.albumsRecyclerView.adapter = AlbumAdapter(this, albums) { albumViewModel.onAlbumClick(it) }
        }

        albumViewModel.progressVisible.observe(this) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }

        albumViewModel.showMessage.observe(this) {
            //TODO llamar a detalle de album
            //Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            sendMessage(it)
        }

        albumViewModel.onCreate()
    }

    /** Called when the user taps the Send button */
    private fun sendMessage(message: String) {
        val intent = Intent(this, AlbumDetailActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}
