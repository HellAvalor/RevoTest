package com.andrewkaraman.rtest.net

import com.andrewkaraman.rtest.model.RevoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("/latest")
    fun fetchQuestions(@Query("base") tags: String): Call<RevoModel>
}
