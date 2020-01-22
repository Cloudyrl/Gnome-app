package com.example.gnomes.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.gnomes.R
import com.example.gnomes.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {


    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DetailFragmentBinding.inflate(inflater)
        val application = requireNotNull(activity).application
        binding.setLifecycleOwner(this)
        return binding.root
    }

}
