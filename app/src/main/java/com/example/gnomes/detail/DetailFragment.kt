package com.example.gnomes.detail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.gnomes.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {


    private lateinit var viewModel: DetailViewModel

    /* Se obtiene el Gnome seleccionado y mediante safeArgs para evitar errores de casteo
    * luego se agrega a la vista usando Databinding y luego se agregan la lista de profesiones y amigos
    * a su respectivo adaptador del recyclerView*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = DetailFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val gnomeModel = DetailFragmentArgs.fromBundle(arguments!!).selectedGnome
        val viewModelFactory = DetailViewModelFactory(gnomeModel, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(DetailViewModel::class.java)
        binding.professionslist.adapter = GnomeListAdapter(gnomeModel.professions, application)
        binding.friendslist.adapter = GnomeListAdapter(gnomeModel.friends, application)
        return binding.root
    }
}
