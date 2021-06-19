package com.andrepc.desafio_elefante.model.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.andrepc.desafio_elefante.model.local.db.StepRoomDatabase
import com.andrepc.desafio_elefante.model.local.dao.StepDao
import com.andrepc.desafio_elefante.model.local.entity.Step

/**
 * Created by André Castro
 */

/**
 * Repository class to keep text from Api request for each step position in the local database
 * Also provides access to the data from local table "step" using LiveData
 */
class StepRepository(application: Application) {

    // Access to the data from Room Database
    private val dao: StepDao

    init {
        val database = StepRoomDatabase.getInstance(application)
        dao = database!!.stepDao()
    }

    /**
     * Add a step to the Room Database
     */
    suspend fun insertStep(step: Step) {
        dao.insert(step)
    }

    /**
     * Retrieve the step from Room Database
     */
    fun getStep(): LiveData<List<Step?>> {
        return dao.select()
    }

}