package com.grupo19.ingsoftmoviles.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.grupo19.ingsoftmoviles.model.data.CollectorResponse
import com.grupo19.ingsoftmoviles.R

class CollectorListAdapter(private val context: Context, private val collectorsList: List<CollectorResponse>, val clickListener: (CollectorResponse) -> Unit): RecyclerView.Adapter<CollectorListAdapter.CollectorViewHolder>() {

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.collector_list_item, parent, false)
        return CollectorViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        val collector = collectorsList[position]
        holder.collectorNameTextView.text = collector.name
        holder.collectorEmailTextView.text = collector.email
        //arrow image goes here?
        holder.collectorCardView.setOnClickListener{ clickListener(collector) }
    }

    override fun getItemCount(): Int {
        return collectorsList.size
    }

    class CollectorViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        val collectorNameTextView: TextView = view.findViewById(R.id.collector_name)
        val collectorEmailTextView: TextView = view.findViewById(R.id.collector_email)
        //pending static arrow image
        val collectorCardView: MaterialCardView = view.findViewById(R.id.collector_card)
    }

}