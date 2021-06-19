package com.andrepc.desafio_elefante.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by André Castro
 */

class StepUtils {

    // Format of the date
    private val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    /**
     * Check if the current date is after the stored date param
     */
    fun isAfterCurrentDate(storedDate: String): Boolean {
        val currentDate = simpleDateFormat.parse(getCurrentDate())
        return currentDate.after(simpleDateFormat.parse(storedDate))
    }

    /**
     * Get the current date
     */
    fun getCurrentDate(): String{
        return simpleDateFormat.format(Date())
    }

}