package com.jantzen.example.gamerelease.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jantzen.example.gamerelease.FragmentUebersichtDirections
import com.jantzen.example.gamerelease.R
import com.jantzen.example.gamerelease.data.model.Game

class GameAdapterUebersicht() : RecyclerView.Adapter<GameAdapterUebersicht.ViewHolder>() {

    private var dataset = listOf<Game>()

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.game_image_uebersicht)
        val name: TextView = view.findViewById(R.id.game_name_uebersicht)
        val date: TextView = view.findViewById(R.id.game_date_uebersicht)
        //val toolbar: MaterialToolbar = view.findViewById(R.id.materialToolbar_uebersicht)
        val cardview: CardView = view.findViewById(R.id.cardView_game_detail)




    }

    fun submitList(newList: List<Game>) {
        dataset = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_uebersicht, parent, false)
        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = dataset[position]
        holder.cardview.setOnClickListener {
            findNavController(it).navigate(FragmentUebersichtDirections.actionFragmentUebersichtToFragmentGame(game.name!!))
        }

        try {
            holder.name.text = game.name
            holder.date.text = game.expected_release_year.toString()
        } catch (e: Exception) {

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

            }
        }catch (e: Exception){
            //TODO platzhalter einfügen
        }
    }
        override fun getItemCount(): Int {
            return dataset.size
        }
    }
