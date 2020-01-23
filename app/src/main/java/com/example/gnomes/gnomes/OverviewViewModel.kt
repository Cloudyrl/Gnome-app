package com.example.gnomes.gnomes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gnomes.network.GnomeApi
import com.example.gnomes.network.GnomeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

enum class GnomeApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<GnomeApiStatus>()
    val status: LiveData<GnomeApiStatus>
        get() = _status

    private val _gnomeList = MutableLiveData<List<GnomeModel>>()
    val gnomeList: LiveData<List<GnomeModel>>
        get() = _gnomeList

    private val _navigateToSelectedGnome = MutableLiveData<GnomeModel>()
    val navigateToSelectedGnome: LiveData<GnomeModel>
        get() = _navigateToSelectedGnome

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getGnomeList()
    }

    private fun getGnomeList() {
        coroutineScope.launch {
            var getGnomesDeferred = GnomeApi.retrofitService.getGnomes()
            try {
                _status.value = GnomeApiStatus.LOADING
                var listResult = getGnomesDeferred.await()
                _status.value = GnomeApiStatus.DONE
                _gnomeList.value = listResult.gnomeList
            } catch (e: Exception) {
                _status.value = GnomeApiStatus.ERROR
                _gnomeList.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayGnomeDetails(gnomeModel: GnomeModel) {
        _navigateToSelectedGnome.value = gnomeModel
    }

    fun displayGnomeDetailsComplete() {
        _navigateToSelectedGnome.value = null
    }
}
