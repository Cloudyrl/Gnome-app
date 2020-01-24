package com.example.gnomes.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gnomes.network.GnomeModel

//ViewModel de la vista de detalles
class DetailViewModel(gnomeModel: GnomeModel, app: Application) : AndroidViewModel(app) {
    private val _selectedGnome = MutableLiveData<GnomeModel>()
    val selectedGnome: LiveData<GnomeModel>
        get() = _selectedGnome

    init {
        _selectedGnome.value = gnomeModel
    }
    //redondea a dos decimales
    fun displayWeight(): String {
        return "%.2f".format(_selectedGnome.value?.weight)
    }
    //redondea a dos decimales
    fun displayHeight(): String {
        return "%.2f".format(_selectedGnome.value?.height)
    }
    //convierte la edad en un string
    fun displayAge(): String {
        return _selectedGnome.value?.age.toString()
    }
}
