package com.jantzen.example.gamerelease.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.jantzen.example.gamerelease.R
import com.jantzen.example.gamerelease.data.model.Game
import com.jantzen.example.gamerelease.data.model.Game_Master

class GameAdapterUebersicht() : RecyclerView.Adapter<GameAdapterUebersicht.ItemViewHolder>(){

    private var dataset = listOf<Game_Master>()

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

        val cover: ImageView = view.findViewById(R.id.game_image)
        val name: TextView = view.findViewById(R.id.game_name_uebersicht)
        val date: TextView = view.findViewById(R.id.game_date_uebersicht)
        val storyline: TextView = view.findViewById(R.id.game_description_text)
        val publisher: TextView = view.findViewById(R.id.game_publisher)
        val toolbar: MaterialToolbar = view.findViewById(R.id.materialToolbar_uebersicht)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_game,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val game = dataset[position]
        holder.name.text = game.name
        holder.date.text = game.date
        holder.storyline.text = game.storyline
        holder.publisher.text = game.publisher
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}