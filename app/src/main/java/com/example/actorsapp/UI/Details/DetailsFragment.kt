package com.example.actorsapp.UI.Details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.actorsapp.API.Models.ActorDetailsModel
import com.example.actorsapp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment() : Fragment() {

    private val detailsViewModel: DetailsViewModel by viewModel()
    private lateinit var buttonAddToFavorites: ImageButton
    private lateinit var actorDetails: ActorDetailsModel
    private lateinit var views: View

    private var actorId: Int = 10
    private var fav: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel::class.java)

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

        detailsViewModel.register.observe(viewLifecycleOwner, Observer {
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

                    detailsViewModel.deleteActorFromFav(actorDetails)
                } else {

                    detailsViewModel.registerActorToFav(actorDetails)
                }
            }

        }

        detailsViewModel.getPostFromAPiTest(actorId)
    }



}
