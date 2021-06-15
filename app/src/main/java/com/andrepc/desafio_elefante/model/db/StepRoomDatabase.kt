package com.andrepc.desafio_elefante.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andrepc.desafio_elefante.model.entity.Elefante
import com.andrepc.desafio_elefante.model.entity.ElefanteDao

/**
 * Create by André Castro
 */
@Database(
    entities = [
        Elefante::class
    ], version = 1
)
abstract class StepRoomDatabase : RoomDatabase() {

    abstract fun elefanteDao(): ElefanteDao

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