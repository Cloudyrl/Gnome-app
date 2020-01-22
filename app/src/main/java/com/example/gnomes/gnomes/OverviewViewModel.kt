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

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _gnomeList = MutableLiveData<GnomeModel>()
    val gnomeList: LiveData<GnomeModel>
        get() = _gnomeList

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init{
        getGnomeList()
    }

    private fun getGnomeList() {
        coroutineScope.launch {
            var getGnomesDeferred = GnomeApi.retrofitService.getGnomes()
            try{

                var listResult = getGnomesDeferred.await()
                if (listResult.gnomeList.size > 0) {
                    _gnomeList.value = listResult.gnomeList[0]
                }
            } catch (e: Exception){
                _status.value = "Failure ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
