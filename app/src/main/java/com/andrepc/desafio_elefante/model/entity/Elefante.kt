package com.andrepc.desafio_elefante.model.entity

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by André Castro
 */

@Entity(tableName = "elefante")
data class Elefante(

    @ColumnInfo(name = "posicao")
    var posicao: Int,

    @ColumnInfo(name = "texto")
    var texto: String
)

interface ElefanteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(elefante: Elefante)

    @Delete
    suspend fun delete(elefante: Elefante)

    @Query("SELECT * FROM elefante")
    fun select(): LiveData<Elefante>

}