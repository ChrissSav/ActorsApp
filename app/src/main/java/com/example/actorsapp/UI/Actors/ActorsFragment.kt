package com.example.actorsapp.UI.Actors

import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.actorsapp.API.Models.ActorModel
import com.example.actorsapp.R
import com.example.actorsapp.UI.ActorsAdapter
import androidx.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import com.example.actorsapp.ApplicationClass
import com.example.actorsapp.Data.DAO.ActorDAO


class ActorsFragment : Fragment() {

    private var navController: NavController? = null
    private lateinit var actorsViewModel: ActorsViewModel
    private lateinit var recyclerView: RecyclerView
    private var exampleListTests: ArrayList<Pair<ActorModel, Boolean>> = ArrayList()

    private var page: Int = 1
    private lateinit var progressBar: ProgressBar
    private lateinit var buttonFav: Button
    private lateinit var dataBase: ActorDAO

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        actorsViewModel = ViewModelProviders.of(this).get(ActorsViewModel::class.java)
        //exampleListTests.clear()
        //page = 1
        return inflater.inflate(R.layout.fragment_actors, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val v = view.context.applicationContext as ApplicationClass
        dataBase = v.db.currentActorDao()
        navController = Navigation.findNavController(view)
        recyclerView = view.findViewById(R.id.recycler_view)
        progressBar = view.findViewById(R.id.progressBar)
        buttonFav = view.findViewById(R.id.button_ActorsFragment)
        createRecycleView()

        actorsViewModel.postList.observe(viewLifecycleOwner, Observer {
            exampleListTests.addAll(it)
            progressBar.visibility = View.GONE
            recyclerView.adapter?.notifyDataSetChanged()
            //createRecycleView()
        })

        if (exampleListTests.size < 1){
            Log.i("estila","1")
            actorsViewModel.getActorsFromApi(page, dataBase)
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {

                    progressBar.visibility = View.VISIBLE
                    page++
                    Log.i("estila","2")

                    actorsViewModel.getActorsFromApi(page, dataBase)

                }
            }
        })

        buttonFav.setOnClickListener {
            navController!!.navigate(R.id.action_actorsFragment_to_favoritesFragment)
        }
    }


    private fun createRecycleView() {
        recyclerView.adapter = ActorsAdapter(exampleListTests)
        {
            val actorId = it.id
            val v = exampleListTests.filter { t ->
                t.first.id == actorId
            }
            val bundle =
                bundleOf("actorId" to it.id, "favorite" to v[0].second)

            navController!!.navigate(R.id.next_fra, bundle)
        }
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
    }


}
