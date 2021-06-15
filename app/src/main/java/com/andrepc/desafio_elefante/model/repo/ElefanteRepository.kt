package com.andrepc.desafio_elefante.model.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.andrepc.desafio_elefante.model.db.StepRoomDatabase
import com.andrepc.desafio_elefante.model.entity.Elefante
import com.andrepc.desafio_elefante.model.entity.ElefanteDao

/**
 * Created by André Castro
 */

/**
 * Repository class for keeping changes of the elephant position in the database
 * Also provides access to the data
 */
class ElefanteRepository(application: Application) {

    // Access to the data
    private val dao: ElefanteDao

    init {
        val database = StepRoomDatabase.getInstance(application)
        dao = database!!.elefanteDao()
    }

    /**
     * Add a 'Elefante' to the database
     */
    suspend fun insertElefante(elefante: Elefante) {
        dao.insert(elefante)
    }

    /**
     * Delete specific 'Elefante' from Room Database
     */
    suspend fun deleteElefante(elefante: Elefante) {
        dao.delete(elefante)
    }

    /**
     * Retrieve the 'Elefante' from Room Database
     */
    fun selectElefante(): LiveData<Elefante> {
        return dao.select()
    }

}