package com.andrepc.desafio_elefante.model.remote.service

import com.andrepc.desafio_elefante.model.remote.response.Facts
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by André Castro
 */

/**
 * Api service to get Facts from the Endpoint
 */
interface FactsService {

    @GET("/facts")
    suspend fun getFacts(): Response<List<Facts>>

}