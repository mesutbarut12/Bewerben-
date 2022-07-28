package de.barut.mybeworbenapp.repository

import androidx.lifecycle.MutableLiveData
import de.barut.mybeworbenapp.entities.BewerbenModel
import de.barut.mybeworbenapp.room.RoomDAO
import javax.inject.Inject

class DataRepository @Inject constructor(
    val dao : RoomDAO) {

    var liveData = MutableLiveData<List<BewerbenModel>>()


    suspend fun getDatas() : MutableLiveData<List<BewerbenModel>>{
        val allDatas = dao.getDatas()
        liveData.value = allDatas
        return liveData
    }
    suspend fun deleteDatas(model : BewerbenModel){
        var deleteDatas = dao.deleteData(model)
    }
    suspend fun editDatas(model : BewerbenModel){
        var editDatas = dao.updateData(model)
    }
    suspend fun addDatas(model : BewerbenModel){
        var addDatas = dao.addData(model)
    }

}