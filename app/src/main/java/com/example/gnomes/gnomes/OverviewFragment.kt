package com.example.gnomes.gnomes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.gnomes.databinding.OverviewFragmentBinding

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProviders.of(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        //obtiene el binding object con los campos y componentes usados en el layout
        val binding = OverviewFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        //ClickListener de las tarjetas del Grid para mostrar el detalle
        binding.gnomesGrid.adapter = GnomesGridAdapter(GnomesGridAdapter.OnClickListener {
            viewModel.displayGnomeDetails(it)
        })

        //Observa los cambios en Gnome seleccionado para mostrar la vista de detalle
        viewModel.navigateToSelectedGnome.observe(this, Observer {
            if (null != it) {
                this.findNavController()
                    .navigate(OverviewFragmentDirections.actionOverviewFragmentToDetailFragment(it))
                viewModel.displayGnomeDetailsComplete()
            }
        })
        return binding.root
    }
}
