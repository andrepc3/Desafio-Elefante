package com.andrepc.desafio_elefante.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.andrepc.desafio_elefante.model.remote.response.Facts
import com.andrepc.desafio_elefante.model.local.entity.Elefante
import com.andrepc.desafio_elefante.model.local.entity.Expiration
import com.andrepc.desafio_elefante.model.repo.ElefanteRepository
import com.andrepc.desafio_elefante.model.repo.FactsRepository
import com.andrepc.desafio_elefante.model.repo.ExpirationRepository
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

    private val elefanteRepository: ElefanteRepository = ElefanteRepository(application)
    private val expirationRepository: ExpirationRepository = ExpirationRepository(application)
    private val factsRepository: FactsRepository = FactsRepository()
    private val stepUtils: StepUtils = StepUtils()

    fun insertElefante(posicaoParam: Int, textoParam: String) = viewModelScope.launch(Dispatchers.IO) {
        elefanteRepository.insertLocalElefante(
            Elefante(
                posicao = posicaoParam,
                texto = textoParam
            )
        )
    }

    fun getElefante(): LiveData<List<Elefante>> {
        return elefanteRepository.getLocalElefante()
    }

    fun insertExpiration(dateParam: String) = viewModelScope.launch(Dispatchers.IO) {
        expirationRepository.insertExpiration(
            Expiration(
                id = 1,
                date = dateParam
            )
        )
    }

    fun getExpiration(): LiveData<List<Expiration>> {
        return expirationRepository.getExpiration()
    }

    fun getFacts(): LiveData<List<Facts>> {
        return factsRepository.getEndpointFacts()
    }

    fun isAfterCurrentDate(storedDate: String): Boolean {
        return stepUtils.isAfterCurrentDate(storedDate)
    }

    fun getCurrentDate(): String {
        return stepUtils.getCurrentDate()
    }

    fun setOnClickPosition(posicao: Int) {
        stepClickedPosition.value = posicao
    }

    fun getClickedPosition(): LiveData<Int> {
        return stepClickedPosition
    }

}