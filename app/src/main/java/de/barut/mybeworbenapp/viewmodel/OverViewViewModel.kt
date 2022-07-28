package de.barut.mybeworbenapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.barut.mybeworbenapp.entities.BewerbenModel
import de.barut.mybeworbenapp.repository.DataRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class OverViewViewModel @Inject constructor(
    val dRepository: DataRepository
) : ViewModel() {

    init {
        get()
    }

    var liveData = MutableLiveData<List<BewerbenModel>>()

    fun get() {
        viewModelScope.launch {
            val datas = dRepository.getDatas()
            liveData = datas
        }
    }

    fun add(model: ArrayList<BewerbenModel>) {
        viewModelScope.launch {
            for (i in model) {
                dRepository.addDatas(i)
            }
            get()
        }
    }

    fun delete(model : BewerbenModel){
        viewModelScope.launch{
            dRepository.deleteDatas(model)
           get()
        }
    }
}