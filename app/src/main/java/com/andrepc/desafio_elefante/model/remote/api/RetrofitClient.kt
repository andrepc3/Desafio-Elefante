package com.andrepc.desafio_elefante.model.remote.api

import com.andrepc.desafio_elefante.model.remote.response.Facts
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by André Castro
 */

class RetrofitClient {

    companion object {

        private const val URL: String = "https://cat-fact.herokuapp.com"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

}