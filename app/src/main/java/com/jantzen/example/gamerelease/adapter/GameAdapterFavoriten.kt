package com.jantzen.example.gamerelease.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.jantzen.example.gamerelease.R
import com.jantzen.example.gamerelease.data.model.Game

class GameAdapterFavoriten : RecyclerView.Adapter<GameAdapterFavoriten.ItemViewHolder>(){
    private var dataset = listOf<Game>()

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

        val cover: ImageView = view.findViewById(R.id.game_image)
        val name: TextView = view.findViewById(R.id.game_name_favorite)
        val date: TextView = view.findViewById(R.id.game_date_favorite)
        val fav: ImageButton = view.findViewById(R.id.imageButton_fav)
        val toolbar: MaterialToolbar = view.findViewById(R.id.materialToolbar_fav)
        val likeButton: ImageButton = view.findViewById(R.id.imageButton_fav)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_game,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val game = dataset[position]
        holder.name.text = game.name
       // holder.date.text = game.date
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    
}