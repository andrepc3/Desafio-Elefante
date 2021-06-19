package com.andrepc.desafio_elefante.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andrepc.desafio_elefante.model.local.entity.Elefante

/**
 * Created by André Castro
 */

@Dao
interface ElefanteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(elefante: Elefante)

    @Delete
    suspend fun delete(elefante: Elefante)

    @Query("UPDATE elefante SET texto = :texto WHERE posicao = :posicao")
    suspend fun updateTexto(texto: String, posicao: Int)

    @Query("SELECT * FROM elefante")
    fun select(): LiveData<List<Elefante>>

}