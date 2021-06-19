package com.andrepc.desafio_elefante.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by André Castro
 */

@Entity(tableName = "step")
data class Step(

    @PrimaryKey
    @ColumnInfo(name = "position")
    var position: Int,

    @ColumnInfo(name = "message")
    var message: String,

)