package com.andrepc.desafio_elefante.model.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.andrepc.desafio_elefante.model.local.dao.ExpirationDao
import com.andrepc.desafio_elefante.model.local.db.StepRoomDatabase
import com.andrepc.desafio_elefante.model.local.entity.Expiration

/**
 * Created by André Castro
 */

/**
 * Repository class to save expiration date when the Api is requested
 * Also provides access to the data using LiveData
 */
class ExpirationRepository(application: Application) {

    // Access to the data from Room Database
    private val dao: ExpirationDao

    init {
        val database = StepRoomDatabase.getInstance(application)
        dao = database!!.expirationDao()
    }

    /**
     * Add the expiration  to the Room Database
     */
    suspend fun insertExpiration(expiration: Expiration) {
        dao.insert(expiration)
    }

    /**
     * Retrieve the expiration from Room Database
     */
    fun getExpiration(): LiveData<List<Expiration>> {
        return dao.select()
    }

}