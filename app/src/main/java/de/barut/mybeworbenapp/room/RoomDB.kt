package de.barut.mybeworbenapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import de.barut.mybeworbenapp.entities.BewerbenModel

@Database(entities = [BewerbenModel::class], version = 2)
abstract class RoomDB : RoomDatabase() {

    abstract fun dao() : RoomDAO


}

