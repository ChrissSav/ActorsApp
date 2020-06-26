package com.example.actorsapp.UI

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.actorsapp.API.Models.ActorModel
import com.example.actorsapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.actor_item.view.*


class ActorsAdapter(
    private val exampleList: ArrayList<Pair<ActorModel, Boolean>>,
    val clickListener: (ActorModel) -> Unit
) :
    RecyclerView.Adapter<ActorsAdapter.ExampleViewHolder>() {
    var onItemClick: ((ActorModel) -> Unit)? = null

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
        holder.setIsRecyclable(false)
        val currentItem = exampleList[position]
        holder.textViewName.text = currentItem.first.name
        holder.textViewPopularity.text = currentItem.first.popularity.toString()

        Picasso.with(this.context)
            .load("https://image.tmdb.org/t/p/w500" + currentItem.first.profilePath)
            .into(holder.circleImageView)


        if (currentItem.second)
            holder.imageViewStar.visibility = View.VISIBLE

        holder.circleImageView.setOnClickListener { clickListener(currentItem.first) }


    }

    override fun getItemCount() = exampleList.size


    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.textView_name
        val textViewPopularity: TextView = itemView.textView_popularity
        val circleImageView: CircleImageView = itemView.CircleImageView
        val imageViewStar: ImageView = itemView.imageView3

    }
}