package com.example.actorsapp.UI.Favorites


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.actorsapp.Data.Entities.RoomActor
import com.example.actorsapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.actor_item.view.*


class FavActorsAdapter(
    private val exampleList: List<RoomActor>,
    val clickListener: (RoomActor) -> Unit
) :
    RecyclerView.Adapter<FavActorsAdapter.ExampleViewHolder>() {
    var onItemClick: ((RoomActor) -> Unit)? = null

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.actor_item,
            parent, false
        )
        context = parent.context
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        holder.textViewName.text = currentItem.name
        holder.textViewPopularity.text = currentItem.popularity.toString()

        Picasso.with(context)
            .load("https://image.tmdb.org/t/p/w500" + currentItem.profilePath)
            .into(holder.imageView, object : Callback {
                override fun onSuccess() {
                    Log.d("TAG", "success")
                }

                override fun onError() {
                    Log.d("TAG", "error")
                }
            })


        holder.imageView.setOnClickListener { clickListener(currentItem) }


    }

    override fun getItemCount() = exampleList.size


    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.textView_name
        val textViewPopularity: TextView = itemView.textView_popularity
        val imageView: ImageView = itemView.imageView
    }
}