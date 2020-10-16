package ru.app.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import ru.app.models.MovieResponse

interface Api {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Deferred<Response<MovieResponse>>
}