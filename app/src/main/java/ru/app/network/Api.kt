package ru.app.network

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query
import ru.app.models.ResponseMovie

interface Api {

    @GET("/3/search/movie")
    fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("query") query: String,
        @Query("page") page: Int

    ): Call<ResponseMovie>
}