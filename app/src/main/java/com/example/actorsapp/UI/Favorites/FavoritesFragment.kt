package com.example.actorsapp.UI.Favorites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actorsapp.Data.Entities.RoomActor
import com.example.actorsapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoritesFragment : Fragment() {

    private  val viewModel:FavoritesViewModel by viewModel()

    private lateinit var recyclerView: RecyclerView
    private lateinit var views: View
    private lateinit var textView: TextView
    private var actorsList: List<RoomActor> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views = view
        recyclerView = view.findViewById(R.id.recycler_view_FavoritesFragment)
        textView = view.findViewById(R.id.FavoritesFragment_textView_sum_of_fav)
        //

        viewModel.actorList.observe(viewLifecycleOwner, Observer {
            actorsList = it
            textView.text = actorsList.size.toString()

            Log.i("takis",actorsList.size.toString())
            createRecycleView()
        })



        viewModel.getPostFromDataBase()
    }


    private fun createRecycleView() {
        recyclerView.adapter = FavActorsAdapter(actorsList){
            val navController = Navigation.findNavController(views)
            val bundle = bundleOf("actorId" to it.id,"favorite" to true)

            navController!!.navigate(R.id.action_favoritesFragment_to_detailsFragment, bundle)

        }
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
    }

}