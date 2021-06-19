package com.andrepc.desafio_elefante.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by André Castro
 */

@Entity(tableName = "elefante")
data class Elefante(

    @PrimaryKey
    @ColumnInfo(name = "posicao")
    var posicao: Int,

    @ColumnInfo(name = "texto")
    var texto: String,

)