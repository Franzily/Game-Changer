package com.jantzen.example.gamerelease.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jantzen.example.gamerelease.R
import com.jantzen.example.gamerelease.data.model.Game

class GameAdapter() : RecyclerView.Adapter<GameAdapter.ItemViewHolder>(){

    private var dataset = listOf<Game>()

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

        val image: ImageView = view.findViewById(R.id.game_image)
        val name: TextView = view.findViewById(R.id.game_name_uebersicht)
        val date: TextView = view.findViewById(R.id.game_date_uebersicht)
        val description: TextView = view.findViewById(R.id.game_description_text)
        val publisher: TextView = view.findViewById(R.id.game_publisher)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_game,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val game = dataset[position]
        holder.name.text = game.name
        holder.date.text = game.releaseDate
        holder.description.text = game.description
        holder.publisher.text = game.publisher
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}