package com.example.gnomes.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gnomes.network.GnomeModel

class DetailViewModel(gnomeModel: GnomeModel,app: Application):AndroidViewModel(app) {
    private val _selectedGnome = MutableLiveData<GnomeModel>()
    val selectedGnome: LiveData<GnomeModel>
        get() = _selectedGnome

    init {
        _selectedGnome.value = gnomeModel
    }

    fun displayWeight(): String{
        return "%.2f".format(_selectedGnome.value?.weight)
    }

    fun displayHeight(): String{
        return "%.2f".format(_selectedGnome.value?.height)
    }

    fun displayAge(): String{
        return _selectedGnome.value?.age.toString()
    }
}
