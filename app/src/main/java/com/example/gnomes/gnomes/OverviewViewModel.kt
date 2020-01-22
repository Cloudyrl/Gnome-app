package com.example.gnomes.gnomes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gnomes.network.GnomeApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

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
                _response.value = "Success: ${listResult.gnomeList.size} Gnomes retrived"
            } catch (e: Exception){
                _response.value = "Failure ${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
