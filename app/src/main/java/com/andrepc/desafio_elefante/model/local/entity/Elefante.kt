package com.andrepc.desafio_elefante.model.entity

import androidx.room.*

/**
 * Created by André Castro
 */

@Entity(tableName = "elefante")
data class Elefante(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "posicao")
    var posicao: Int,

    @ColumnInfo(name = "texto")
    var texto: String,

    @ColumnInfo(name = "data")
    var data: String
)