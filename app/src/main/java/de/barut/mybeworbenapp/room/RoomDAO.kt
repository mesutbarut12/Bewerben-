package de.barut.mybeworbenapp.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import de.barut.mybeworbenapp.entities.BewerbenModel

@Dao
interface RoomDAO {

    @Insert
    suspend fun addData(model : BewerbenModel)

    @Delete
    suspend fun deleteData(model : BewerbenModel)

    @Update
    suspend fun updateData(model : BewerbenModel)

    @Query("SELECT * FROM bewerben")
    suspend fun getDatas() : List<BewerbenModel>


}