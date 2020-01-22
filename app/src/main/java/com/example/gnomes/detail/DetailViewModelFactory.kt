package com.example.gnomes.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gnomes.network.GnomeModel
import java.lang.IllegalArgumentException

class DetailViewModelFactory(
    private val gnomeModel: GnomeModel,
    private val application: Application) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(gnomeModel,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}