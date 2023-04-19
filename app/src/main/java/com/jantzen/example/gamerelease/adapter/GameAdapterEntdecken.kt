package com.jantzen.example.gamerelease.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jantzen.example.gamerelease.Fragment_entdeckenDirections
import com.jantzen.example.gamerelease.R
import com.jantzen.example.gamerelease.data.model.Game

class GameAdapterEntdecken : RecyclerView.Adapter<GameAdapterEntdecken.ViewHolder>(){

    private var dataset = listOf<Game>()

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

        val image: ImageView = view.findViewById(R.id.game_image_entdecken)
        val name: TextView = view.findViewById(R.id.game_name_entdecken)
        val date: TextView = view.findViewById(R.id.game_date_entdecken)
        val cardviewEntdecken: CardView = view.findViewById(R.id.cardView)
        //val toolbar: MaterialToolbar = view.findViewById(R.id.materialToolbar_entdecken)
    }
    fun submitList(newList: List<Game>) {
        dataset = newList
        println("adapter")
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entdecken,parent,false)
        return ViewHolder(adapterLayout)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = dataset[position]

        holder.cardviewEntdecken.setOnClickListener {
            Navigation.findNavController(it).navigate(Fragment_entdeckenDirections.actionFragmentEntdeckenToFragmentGame(game))
        }
        try {
            holder.name.text = game.name
            holder.date.text = game.expected_release_year.toString()

        } catch (e: Exception){

        }
        try {
            if (game.image!!.medium_url != null) {
                val imageURI = game.image.medium_url!!.toUri().buildUpon().scheme("https").build()
                holder.image.load(imageURI)

            } else if (game.image!!.super_url != null) {
                val imageURI = game.image.super_url!!.toUri().buildUpon().scheme("https").build()
                holder.image.load(imageURI)

            } else if (game.image!!.small_url != null) {
                val imageURI = game.image.small_url!!.toUri().buildUpon().scheme("https").build()
                holder.image.load(imageURI)


            } else if (game.image!!.original_url != null) {
                val imageURI = game.image.original_url!!.toUri().buildUpon().scheme("https").build()
                holder.image.load(imageURI)

            } else {
                holder.image.setImageResource(R.drawable.bg_color)
            }
        }catch (e: Exception){
            //TODO platzhalter einfügen
        }
    }
    override fun getItemCount(): Int {
        return dataset.size
    }
}