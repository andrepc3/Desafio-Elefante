package com.andrepc.desafio_elefante.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andrepc.desafio_elefante.model.local.entity.Expiration

/**
 * Created by André Castro
 */

@Dao
interface ExpirationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expiration: Expiration)

    @Query("SELECT * FROM expiration")
    fun select(): LiveData<List<Expiration>>

}