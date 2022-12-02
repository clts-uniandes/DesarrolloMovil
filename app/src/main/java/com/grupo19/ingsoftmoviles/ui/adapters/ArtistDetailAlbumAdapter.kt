package com.grupo19.ingsoftmoviles.ui.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse

class ArtistDetailAlbumAdapter(private val context: Context,
                               private val albumsList: List<ArtistResponse.AlbumResponse>): RecyclerView.Adapter<ArtistDetailAlbumAdapter.ArtistDetailAlbumViewHolder>() {

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistDetailAlbumViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.artist_detail_album_item, parent, false)
        return ArtistDetailAlbumViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ArtistDetailAlbumViewHolder, position: Int) {
        val album = albumsList[position]
        holder.albumNameTextView.text = album.name
        holder.genreTextView.text = album.genre

        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){
            Glide.with(context)
                .load(album.cover)
                .into(holder.imageView)
        }else{
            Glide.with(context)
                .load(album.cover.toUri().buildUpon().scheme("https").build())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.ic_broken_image))
                .into(holder.imageView)
        }
    }

    override fun getItemCount(): Int {
        return albumsList.size
    }

    class ArtistDetailAlbumViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val albumNameTextView: TextView = view.findViewById(R.id.artist_detail_album_name)
        val genreTextView: TextView = view.findViewById(R.id.artist_detail_album_genre)
        val imageView: ImageView = view.findViewById(R.id.artist_detail_album_item_image)
    }
}