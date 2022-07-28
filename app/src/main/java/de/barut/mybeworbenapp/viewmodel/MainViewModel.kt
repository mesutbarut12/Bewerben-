package de.barut.mybeworbenapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.barut.mybeworbenapp.entities.BewerbenModel
import de.barut.mybeworbenapp.repository.DataRepository
import kotlinx.coroutines.*
import javax.inject.Inject


class MainViewModel @Inject constructor(
    var dataRepository : DataRepository
) : ViewModel() {


    private var _liveData = dataRepository.liveData
    var liveData: MutableLiveData<List<BewerbenModel>>
        get() = _liveData
        private set(value) {}


    fun launchDatas() {
        viewModelScope.launch {
            dataRepository.getDatas()
        }
    }
    fun deleteDatas(model : BewerbenModel){
        viewModelScope.launch {
            dataRepository.deleteDatas(model)
        }
    }
    fun editDatas(model : BewerbenModel){
        viewModelScope.launch {
            dataRepository.editDatas(model)
        }
    }
    fun moveDatas(model : BewerbenModel){

    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}