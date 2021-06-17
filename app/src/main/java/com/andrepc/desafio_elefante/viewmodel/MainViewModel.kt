package com.andrepc.desafio_elefante.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.andrepc.desafio_elefante.model.remote.response.Facts
import com.andrepc.desafio_elefante.model.entity.Elefante
import com.andrepc.desafio_elefante.model.repo.ElefanteRepository
import com.andrepc.desafio_elefante.model.repo.FactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by André Castro
 */

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val elefanteRepository: ElefanteRepository = ElefanteRepository(application)

    private val factsRepository: FactsRepository = FactsRepository()

    fun insertElefante(elefante: Elefante) = viewModelScope.launch(Dispatchers.IO) {
        elefanteRepository.insertLocalElefante(elefante)
    }

    fun deleteElefante(elefante: Elefante) = viewModelScope.launch(Dispatchers.IO){
        elefanteRepository.deleteLocalElefante(elefante)
    }

    fun getElefante(): LiveData<Elefante>{
        return elefanteRepository.getLocalElefante()
    }

    fun getFacts(): LiveData<List<Facts>> {
        return factsRepository.getEndpointFacts()
    }

}