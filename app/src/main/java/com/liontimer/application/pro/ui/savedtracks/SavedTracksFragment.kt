package com.liontimer.application.pro.ui.savedtracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.liontimer.application.pro.R
import com.liontimer.application.pro.data.TrackDatabase


class SavedTracksFragment : Fragment() {

    companion object {
        fun newInstance() = SavedTracksFragment()
    }

    private lateinit var viewModel: SavedTracksViewModel

    lateinit var savedTracksList: ListView
    lateinit var clear: Button
    lateinit var back: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[SavedTracksViewModel::class.java]
        val root = inflater.inflate(R.layout.saved_tracks_fragment, container, false)

        savedTracksList = root.findViewById(R.id.saved_tracks_list)
        clear = root.findViewById(R.id.button_clear)
        back = root.findViewById(R.id.button_back)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Room.databaseBuilder(requireContext(), TrackDatabase::class.java, "tracks_db").build()
        viewModel.setRepository(db)
        viewModel.getAllTracks()

        viewModel.tracks.observe(viewLifecycleOwner){
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
                requireContext(), R.layout.item_view, R.id.itemTextView, it
            )
            savedTracksList.adapter = arrayAdapter
        }

        clear.setOnClickListener {
            viewModel.clearAll()
        }

        back.setOnClickListener {
            findNavController().popBackStack(R.id.nav_main, false)
        }
    }

}