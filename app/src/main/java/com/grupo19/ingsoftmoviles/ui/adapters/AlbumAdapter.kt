package com.grupo19.ingsoftmoviles.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.model.data.Album

class AlbumAdapter(private val context: Context, private val albumsList: List<Album>): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_item, parent, false)
        return AlbumViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumsList[position]
        holder.albumNameTextView.text = album.name
        holder.performersNameTextView.text = album.performers
        Glide.with(context)
            .load(album.cover)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    class AlbumViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val albumNameTextView: TextView = view.findViewById(R.id.album_name)
        val performersNameTextView: TextView = view.findViewById(R.id.performers)
        val imageView: ImageView = view.findViewById(R.id.item_image)
    }
}