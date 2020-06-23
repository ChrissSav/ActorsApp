package com.example.actorsapp.UI.Favorites

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actorsapp.ApplicationClass
import com.example.actorsapp.Data.Entities.RoomActor
import com.example.actorsapp.R

class FavoritesFragment : Fragment() {


    private lateinit var viewModel: FavoritesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var views: View
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
        viewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
        recyclerView = view.findViewById(R.id.recycler_view_FavoritesFragment)
        viewModel.actorList.observe(viewLifecycleOwner, Observer {
            actorsList = it
            Log.i("takis",actorsList.size.toString())
            createRecycleView()
        })

        val repo = view.context.applicationContext as ApplicationClass
        //val nextAction = FavoritesFragmentDirections.nextAction()

        viewModel.getPostFromDataBase(repo.db)
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