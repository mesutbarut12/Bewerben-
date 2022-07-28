package de.barut.mybeworbenapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.barut.mybeworbenapp.entities.BewerbenModel
import de.barut.mybeworbenapp.repository.DataRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddItemViewModel @Inject constructor(
    val dRepository : DataRepository
) : ViewModel() {

    fun add(model : BewerbenModel) {
        viewModelScope.launch {
            dRepository.addDatas(model)
        }
    }

    fun changeBewerbenToString(bewerben : Boolean) : String{
        if(bewerben)
            return "Beworben"
        else
            return "in  bearbeitung"
    }

    //isempty -> false
    //isnotempty -> true
    fun checkUserInputOK(name : String) : Boolean = name == " "






}