package com.example.gnomes.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.gnomes.R
import com.example.gnomes.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {


    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val gnomeModel = DetailFragmentArgs.fromBundle(arguments!!).selectedGnome
        val viewModelFactory = DetailViewModelFactory(gnomeModel,application)
        binding.viewModel = ViewModelProviders.of(
            this,viewModelFactory).get(DetailViewModel::class.java)
        binding.professionslist.adapter = GnomeListAdapter(gnomeModel.professions,application)
        binding.friendslist.adapter = GnomeListAdapter(gnomeModel.friends,application)
        return binding.root
    }

}
