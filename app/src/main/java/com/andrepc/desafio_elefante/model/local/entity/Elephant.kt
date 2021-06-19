package com.andrepc.desafio_elefante.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by André Castro
 */

@Entity(tableName = "elephant")
data class Elephant(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "position")
    var position: Int,

    @ColumnInfo(name = "date")
    var date: String

)
