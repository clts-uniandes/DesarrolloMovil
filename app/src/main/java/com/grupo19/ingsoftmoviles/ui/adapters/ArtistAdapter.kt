package com.grupo19.ingsoftmoviles.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse

class ArtistAdapter(private val context: Context, private val artistList: List<ArtistResponse>): RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.artist_item, parent, false)
        return ArtistViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artistList[position]
        holder.artistName.text = artist.name
        holder.artistDescription.text = artist.description.toString().substring(0,150)
        Glide.with(context).load(artist.image).into(holder.artisImageView)
    }

    override fun getItemCount(): Int {
        return artistList.size
    }

    class ArtistViewHolder(private val view: View): RecyclerView.ViewHolder(view){
        val artistName:TextView = view.findViewById(R.id.artist_name)
        val artistDescription:TextView = view.findViewById(R.id.artist_description)
        val artisImageView: ImageView = view.findViewById(R.id.artist_item_image)
        val artistCardView: MaterialCardView = view.findViewById(R.id.artist_card)
    }
}
