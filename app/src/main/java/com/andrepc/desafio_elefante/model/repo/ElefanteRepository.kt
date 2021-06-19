package com.andrepc.desafio_elefante.model.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.andrepc.desafio_elefante.model.local.db.StepRoomDatabase
import com.andrepc.desafio_elefante.model.local.dao.ElefanteDao
import com.andrepc.desafio_elefante.model.local.entity.Elefante

/**
 * Created by André Castro
 */

/**
 * Repository class to keep changes of the elephant position in the database
 * Also provides access to the data using LiveData
 */
class ElefanteRepository(application: Application) {

    // Access to the data from Room Database
    private val dao: ElefanteDao

    init {
        val database = StepRoomDatabase.getInstance(application)
        dao = database!!.elefanteDao()
    }

    /**
     * Add a "Elefante" to the Room Database
     */
    suspend fun insertLocalElefante(elefante: Elefante) {
        dao.insert(elefante)
    }

    /**
     * Retrieve the "Elefante" from Room Database
     */
    fun getLocalElefante(): LiveData<List<Elefante>> {
        return dao.select()
    }

}