package com.andrepc.desafio_elefante.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andrepc.desafio_elefante.model.entity.Elefante

/**
 * Create by André Castro
 */

@Dao
interface ElefanteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(elefante: Elefante)

    @Delete
    suspend fun delete(elefante: Elefante)

    @Query("SELECT * FROM elefante")
    fun select(): LiveData<Elefante>

}