package com.andrepc.desafio_elefante.model.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.andrepc.desafio_elefante.model.local.dao.ElephantDao
import com.andrepc.desafio_elefante.model.local.db.StepRoomDatabase
import com.andrepc.desafio_elefante.model.local.entity.Elephant

/**
 * Created by André Castro
 */

/**
 * Repository class to save the elephant position and the expiration date when Api is requested
 * Also provides access to the data from local table "elephant" using LiveData
 */
class ElephantRepository(application: Application) {

    // Access to the data from Room Database
    private val dao: ElephantDao

    init {
        val database = StepRoomDatabase.getInstance(application)
        dao = database!!.elephantDao()
    }

    /**
     * Add a elephant to the Room Database
     */
    suspend fun insertElephant(elephant: Elephant) {
        dao.insert(elephant)
    }

    /**
     * Retrieve the elephant from Room Database
     */
    fun getElephant(): LiveData<List<Elephant?>> {
        return dao.select()
    }

    /**
     * Update the elephant position on the Room Database
     */
    suspend fun updateElephantPosition(position: Int){
        dao.updatePosition(position)
    }

    /**
     * Update the elephant date on the Room Database
     */
    suspend fun updateElephantDate(date: String){
        dao.updateDate(date)
    }


}