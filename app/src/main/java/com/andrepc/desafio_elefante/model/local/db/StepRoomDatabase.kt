package com.andrepc.desafio_elefante.model.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andrepc.desafio_elefante.model.local.entity.Step
import com.andrepc.desafio_elefante.model.local.dao.StepDao
import com.andrepc.desafio_elefante.model.local.dao.ElephantDao
import com.andrepc.desafio_elefante.model.local.entity.Elephant

/**
 * Create by André Castro
 */

@Database(
    entities = [
        Step::class,
        Elephant::class
    ], version = 1
)
abstract class StepRoomDatabase : RoomDatabase() {

    abstract fun stepDao(): StepDao
    abstract fun elephantDao(): ElephantDao

    companion object {
        private var INSTANCE: StepRoomDatabase? = null
        private val DB_NAME = "step_room_database"

        fun getInstance(context: Context): StepRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(StepRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        StepRoomDatabase::class.java,
                        DB_NAME
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}