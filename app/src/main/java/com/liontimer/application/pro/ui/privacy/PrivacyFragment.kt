package com.liontimer.application.pro.ui.privacy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.liontimer.application.pro.R
import com.liontimer.application.pro.data.preference.PreferenceManager


class PrivacyFragment : Fragment() {

    companion object {
        fun newInstance() = PrivacyFragment()
    }

    private lateinit var viewModel: PrivacyViewModel

    lateinit var okButton: Button
    lateinit var laterButton: Button
    lateinit var privacyLink: TextView

    lateinit var preference:PreferenceManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PrivacyViewModel::class.java)
        val root = inflater.inflate(R.layout.privacy_fragment, container, false)

        okButton = root.findViewById(R.id.button_ok)
        laterButton = root.findViewById(R.id.button_later)
        privacyLink = root.findViewById(R.id.privacy_link)



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preference = PreferenceManager(requireContext())

        if(preference.getPrivacyAccepted()){
            findNavController().navigate(R.id.nav_main)
        }

        okButton.setOnClickListener {
            preference.setPrivacyAccepted(true)
            findNavController().navigate(R.id.nav_main)
        }

        laterButton.setOnClickListener {
            findNavController().navigate(R.id.nav_main)
        }

        privacyLink.setOnClickListener{
            val url = "https://docs.google.com/document/d/16c7tMk_fOowNXmJrdKsV8tlIxZVkolxhhHzxMgxCKgI/edit?usp=sharing"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}