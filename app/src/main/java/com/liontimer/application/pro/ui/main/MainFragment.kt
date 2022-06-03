package com.liontimer.application.pro.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.liontimer.application.pro.R
import com.liontimer.application.pro.data.TrackDatabase

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    lateinit var startButton: Button
    lateinit var pauseResumeButton: Button
    lateinit var saveButton: Button
    lateinit var savedTracksButton: Button
    lateinit var displayTime: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val root  = inflater.inflate(R.layout.main_fragment, container, false)
        startButton = root.findViewById(R.id.button_start)
        pauseResumeButton = root.findViewById(R.id.button_pause_resume)
        saveButton = root.findViewById(R.id.button_save)
        savedTracksButton = root.findViewById(R.id.button_saved_tracks)
        displayTime = root.findViewById(R.id.display_time)

        startButton.setOnClickListener {
            viewModel.start()
        }

        pauseResumeButton.setOnClickListener {
            viewModel.pauseResume()
        }

        viewModel.displayTime.observe(viewLifecycleOwner){
            displayTime.text = it
        }

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Room.databaseBuilder(requireContext(), TrackDatabase::class.java, "tracks_db").build()
        viewModel.setRepository(db)

        saveButton.setOnClickListener {
            viewModel.saveTrack()
        }

        savedTracksButton.setOnClickListener {
            findNavController().navigate(R.id.nav_saved_tracks)
        }


        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    activity?.moveTaskToBack(true);
                    activity?.finish();
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }

}