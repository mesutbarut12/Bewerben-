package de.barut.mybeworbenapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bewerben")
data class BewerbenModel(
    @PrimaryKey(autoGenerate = true)
    val id : Long? ,
    val beworben: String = "in bearbeitung",
    val name: String = "",
    val date: String = "",
    val description: String = "",
    val contactPerson: String = ""
)