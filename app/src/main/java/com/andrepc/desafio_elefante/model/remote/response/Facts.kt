package com.andrepc.desafio_elefante.model.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Created by André Castro
 */

data class Facts(
    @SerializedName("text")
    var text: String,
)