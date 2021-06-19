package com.andrepc.desafio_elefante.model.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.andrepc.desafio_elefante.model.remote.response.Facts
import com.andrepc.desafio_elefante.model.remote.service.FactsService
import com.andrepc.desafio_elefante.model.remote.api.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * Created by André Castro
 */

/**
 * Repository class to instance Retrofit and get Api request
 */
class FactsRepository() {

    private val retrofitClient = RetrofitClient.getRetrofitInstance()
    private val service = retrofitClient.create(FactsService::class.java)

    /**
     * Retrieve a list of facts from Api using Retrofit
     */
    fun getEndpointFacts(): LiveData<List<Facts>> {
        val factsLiveData = MutableLiveData<List<Facts>>()

        CoroutineScope(IO).launch {
            factsLiveData.postValue(service.getFacts().body())
        }

        return factsLiveData
    }

}