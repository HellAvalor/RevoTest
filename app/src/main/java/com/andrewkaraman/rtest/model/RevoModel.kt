package com.andrewkaraman.rtest.model

import com.google.gson.annotations.SerializedName

data class RevoModel(
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Map<String, Double?>
)