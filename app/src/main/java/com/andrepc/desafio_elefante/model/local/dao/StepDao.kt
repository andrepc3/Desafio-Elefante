package com.andrepc.desafio_elefante.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andrepc.desafio_elefante.model.local.entity.Step

/**
 * Created by André Castro
 */

@Dao
interface StepDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(step: Step)

    @Query("SELECT * FROM step")
    fun select(): LiveData<List<Step?>>

}