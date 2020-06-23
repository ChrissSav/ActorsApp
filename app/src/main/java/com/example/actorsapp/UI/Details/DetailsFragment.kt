package com.example.actorsapp.UI.Details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.actorsapp.API.Models.ActorDetails
import com.example.actorsapp.ApplicationClass
import com.example.actorsapp.Data.Entities.RoomActor
import com.example.actorsapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var buttonAddToFavorites: ImageButton
    private lateinit var actorDetails: ActorDetails
    private lateinit var views: View

    private var actorId: Int = 10
    private var fav: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

        return inflater.inflate(R.layout.fragment_details, container, false)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ff", actorId.toString())
        actorId = requireArguments().getInt("actorId")
        fav = requireArguments().getBoolean("favorite")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views = view


        buttonAddToFavorites = view.findViewById(R.id.imageButton_DetailsFragment)
        if (fav) {
            buttonAddToFavorites.setBackgroundResource(android.R.drawable.btn_star_big_on)
        }

        detailsViewModel.flag.observe(viewLifecycleOwner, Observer {


            val navController = Navigation.findNavController(views)
            navController!!.navigate(R.id.action_detailsFragment_to_actorsFragment)

        })

        detailsViewModel.actor.observe(viewLifecycleOwner, Observer {


            val textViewName: TextView = view.findViewById(R.id.textView_name)
            val textViewPopularity: TextView = view.findViewById(R.id.textView_popularity)
            val textViewPlaceOfBirth: TextView = view.findViewById(R.id.textView_placeOfBirth)
            val textViewBiography: TextView = view.findViewById(R.id.textView_biography)
            val imageView: ImageView = view.findViewById(R.id.imageView2)

            actorDetails = it

            Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w500" + it.profilePath)
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        Log.d("TAG", "success")
                    }

                    override fun onError() {
                        Log.d("TAG", "error")
                    }
                })


            textViewName.text = it.name
            textViewPopularity.text = it.popularity.toString()
            textViewPlaceOfBirth.text = it.placeOfBirth
            textViewBiography.text = it.biography

        })

        buttonAddToFavorites.setOnClickListener {

            CoroutineScope(IO).launch {
                if (fav) {
                    val v = view.context.applicationContext as ApplicationClass
                    detailsViewModel.deleteActorFromFav(v.db.currentActorDao(), actorDetails)
                } else {
                    registerActor(view, actorDetails)
                }
            }

        }

        detailsViewModel.getPostFromAPiTest(actorId)
    }

    private suspend fun registerActor(context: View, actorDetails: ActorDetails) {
        val repo = context.context.applicationContext as ApplicationClass
        val actor = RoomActor(
            actorDetails.id,
            actorDetails.name,
            actorDetails.profilePath,
            actorDetails.popularity
        )
        Log.i("takis", repo.db.currentActorDao().insertOrUpdateActor(actor).toString())
        val navController = Navigation.findNavController(views)
        navController!!.navigate(R.id.action_detailsFragment_to_actorsFragment)
    }

}
