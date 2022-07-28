package de.barut.mybeworbenapp.viewmodel

import androidx.lifecycle.ViewModel
import de.barut.mybeworbenapp.repository.DataRepository
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    val dRepository : DataRepository
) : ViewModel() {

    fun checkDataIsEmptyAndReturnHint(input : String,modelType : String) : String{
        return if(input.isEmpty() || input == " "){
            "$modelType ist leer"
        } else {
            input
        }
    }



}