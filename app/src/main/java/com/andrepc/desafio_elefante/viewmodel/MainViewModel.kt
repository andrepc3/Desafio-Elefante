package com.andrepc.desafio_elefante.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andrepc.desafio_elefante.model.remote.response.Facts
import com.andrepc.desafio_elefante.model.local.entity.Step
import com.andrepc.desafio_elefante.model.local.entity.Elephant
import com.andrepc.desafio_elefante.model.repo.StepRepository
import com.andrepc.desafio_elefante.model.repo.FactsRepository
import com.andrepc.desafio_elefante.model.repo.ElephantRepository
import com.andrepc.desafio_elefante.utils.StepUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by André Castro
 */

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var textStep1: String? = "1"
    var textStep2: String? = "2"
    var textStep3: String? = "3"
    var textStep4: String? = "4"
    var textStep5: String? = "5"

    private val stepClickedPosition = MutableLiveData<Int>()

    private val stepRepository: StepRepository = StepRepository(application)
    private val elephantRepository: ElephantRepository = ElephantRepository(application)
    private val factsRepository: FactsRepository = FactsRepository()
    private val stepUtils: StepUtils = StepUtils()

    /**
     * Call repository insert "step" to add a new one to the room database
     * Uses coroutines for asynchronous database access
     */
    fun insertStep(positionParam: Int, messageParam: String) =
        viewModelScope.launch(Dispatchers.IO) {
            stepRepository.insertStep(
                Step(
                    position = positionParam,
                    message = messageParam
                )
            )
        }

    /**
     * Return the retrieved "step" from Room database
     */
    fun getStep(): LiveData<List<Step?>> {
        return stepRepository.getStep()
    }

    /**
     * Call repository insert "elephant" to add a new one to the room database
     * Uses coroutines for asynchronous database access
     */
    fun insertElephant(positionParam: Int, dateParam: String) =
        viewModelScope.launch(Dispatchers.IO) {
            println("insertElephant***")
            elephantRepository.insertElephant(
                Elephant(
                    id = 1,
                    position = positionParam,
                    date = dateParam
                )
            )
        }

    /**
     * Return the retrieved "elephant" from Room database
     */
    fun getElephant(): LiveData<List<Elephant?>> {
        return elephantRepository.getElephant()
    }

    /**
     * Call repository update to change the actual elephant position
     * Uses coroutines for asynchronous database access
     */
    fun updateElephantPosition(position: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            elephantRepository.updateElephantPosition(position)
        }

    /**
     * Call repository update to change the last Api request date
     * Uses coroutines for asynchronous database access
     */
    fun updateElephantDate(date: String) =
        viewModelScope.launch(Dispatchers.IO) {
            elephantRepository.updateElephantDate(date)
        }

    /**
     * Return the retrieved "facts" from Api request
     */
    fun getFacts(): LiveData<List<Facts>> {
        return factsRepository.getEndpointFacts()
    }

    /**
     * Return if the stored date is after the current date
     */
    fun isAfterCurrentDate(storedDate: String): Boolean {
        return stepUtils.isAfterCurrentDate(storedDate)
    }

    /**
     * Return the current date
     */
    fun getCurrentDate(): String {
        return stepUtils.getCurrentDate()
    }

    /**
     * Click listener for step
     */
    fun setOnClickPosition(posicao: Int) {
        stepClickedPosition.value = posicao
    }

    /**
     * Return the clicked step
     */
    fun getClickedPosition(): LiveData<Int> {
        return stepClickedPosition
    }

}