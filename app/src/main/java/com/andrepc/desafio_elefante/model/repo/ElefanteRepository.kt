package com.andrepc.desafio_elefante.model.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.andrepc.desafio_elefante.model.local.db.StepRoomDatabase
import com.andrepc.desafio_elefante.model.entity.Elefante
import com.andrepc.desafio_elefante.model.local.dao.ElefanteDao

/**
 * Created by André Castro
 */

/**
 * Repository class for keeping changes of the elephant position in the database
 * Also provides access to the data
 */
class ElefanteRepository(application: Application) {

    // Access to the data from Room Database
    private val dao: ElefanteDao

    init {
        val database = StepRoomDatabase.getInstance(application)
        dao = database!!.elefanteDao()
    }

    /**
     * Add a 'Elefante' to the Room Database
     */
    suspend fun insertLocalElefante(elefante: Elefante) {
        dao.insert(elefante)
    }

    /**
     * Delete specific 'Elefante' from Room Database
     */
    suspend fun deleteLocalElefante(elefante: Elefante) {
        dao.delete(elefante)
    }

    /**
     * Retrieve the 'Elefante' from Room Database
     */
    fun getLocalElefante(): LiveData<Elefante> {
        return dao.select()
    }

}