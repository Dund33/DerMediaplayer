package com.lukdro.dermediaplayer

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukdro.dermediaplayer.adapters.TunesListAdapter
import com.lukdro.dermediaplayer.data.IMusicRepo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TunesListFragment : Fragment(R.layout.tunes_list), ListClickListener {

    @Inject
    lateinit var repo: IMusicRepo

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val list = view.findViewById<RecyclerView>(R.id.tunesListRv)
        list.adapter = TunesListAdapter(repo, this)
        list.layoutManager = LinearLayoutManager(context)
    }

    override fun onClick(view:View?, position: Int) {
        val navigationController = view?.findNavController()
        val dirs = TunesListFragmentDirections
            .actionTunesListFragmentToTuneFragment(position)
        navigationController
            ?.navigate(dirs)
    }
}