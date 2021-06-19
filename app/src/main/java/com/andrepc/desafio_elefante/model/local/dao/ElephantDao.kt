package com.andrepc.desafio_elefante.model.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andrepc.desafio_elefante.model.local.entity.Elephant

/**
 * Created by André Castro
 */

@Dao
interface ElephantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(elephant: Elephant)

    @Query("SELECT * FROM elephant")
    fun select(): LiveData<List<Elephant?>>

    @Query("UPDATE elephant SET position = :position WHERE id = 1")
    suspend fun updatePosition(position: Int)

    @Query("UPDATE elephant SET date = :date WHERE id = 1")
    suspend fun updateDate(date: String)

}