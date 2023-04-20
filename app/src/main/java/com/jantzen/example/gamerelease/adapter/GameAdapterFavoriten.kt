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
import com.jantzen.example.gamerelease.Fragment_favoriteDirections
import com.jantzen.example.gamerelease.R
import com.jantzen.example.gamerelease.data.model.Game

class GameAdapterFavoriten(private var dataset: MutableList<Game>) : RecyclerView.Adapter<GameAdapterFavoriten.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.imageView_game_fav)
        val name: TextView = view.findViewById(R.id.game_name_favorite)
        val date: TextView = view.findViewById(R.id.game_date_favorite)
        val cardView: CardView = view.findViewById(R.id.cardview_fav)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val game = dataset[position]
        holder.cardView.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(Fragment_favoriteDirections.actionFragmentFavoriteToFragmentGame(game))
        }
            try {
                holder.name.text = game.name
                holder.date.text = game.expected_release_year.toString()
            } catch (e: Exception) {

            }


            try {
                if (game.image!!.medium_url != null) {
                    val imageURI =
                        game.image.medium_url!!.toUri().buildUpon().scheme("https").build()
                    holder.image.load(imageURI)

                } else if (game.image!!.super_url != null) {
                    val imageURI =
                        game.image.super_url!!.toUri().buildUpon().scheme("https").build()
                    holder.image.load(imageURI)

                } else if (game.image!!.small_url != null) {
                    val imageURI =
                        game.image.small_url!!.toUri().buildUpon().scheme("https").build()
                    holder.image.load(imageURI)


                } else if (game.image!!.original_url != null) {
                    val imageURI =
                        game.image.original_url!!.toUri().buildUpon().scheme("https").build()
                    holder.image.load(imageURI)
                }
            } catch (e: Exception) {
            }
        }
        override fun getItemCount(): Int {
            return dataset.size
        }

        fun submitList(gamesList: MutableList<Game>) {
            dataset = gamesList
            notifyDataSetChanged()
        }


    }
