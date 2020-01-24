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

//Enum para manejar los estados de la llamada al api
enum class GnomeApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    //El status de la llamada al api
    private val _status = MutableLiveData<GnomeApiStatus>()
    val status: LiveData<GnomeApiStatus>
        get() = _status

    //Lista de gnomes obtenidos del api
    private val _gnomeList = MutableLiveData<List<GnomeModel>>()
    val gnomeList: LiveData<List<GnomeModel>>
        get() = _gnomeList

    //El Gnome seleccionado para ser mostrado el detalle
    private val _navigateToSelectedGnome = MutableLiveData<GnomeModel>()
    val navigateToSelectedGnome: LiveData<GnomeModel>
        get() = _navigateToSelectedGnome

    //Trabajo creado para el manejo de la co-rutina de kotlin
    private var viewModelJob = Job()

    //El scope de la corutina, se le pasa el trabajo y el hilo en el que sera ejecutada la co-rutina
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    //se inicializa el modelo con la llamada al api de los Gnomes y con el estatus de carga
    init {
        getGnomeList()
        _status.value = GnomeApiStatus.LOADING
    }

    //Ejecuta la llamada al api dentro de la co-rutina para que no se genere un bloqueo en el UI al ser usado el main Thread
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
    //limpia el trabajo para evitar memory leaks
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    //Pasa el Gnome selecionado a la vista de detalles
    fun displayGnomeDetails(gnomeModel: GnomeModel) {
        _navigateToSelectedGnome.value = gnomeModel
    }
    //Limpia la vista de detalle al regresar
    fun displayGnomeDetailsComplete() {
        _navigateToSelectedGnome.value = null
    }
}
