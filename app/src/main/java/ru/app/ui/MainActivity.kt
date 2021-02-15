package ru.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.app.utils.AppConstants
import ru.app.R
import ru.app.adapters.MovieAdapter
import ru.app.models.ResponseMovie
import ru.app.network.Api
import ru.app.network.RetrofitInstance

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MovieAdapter
    private val service: Api = RetrofitInstance.api
    lateinit var inputQuery: EditText
    lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputQuery = findViewById(R.id.edit_inputQuery)
        searchButton = findViewById(R.id.btn_search)

        recyclerView = findViewById(R.id.rv_movieList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        adapter = MovieAdapter(this)
        recyclerView.adapter = adapter
        searchButton.setOnClickListener {
            getMovie()
        }
    }


    private fun getMovie() {
        val query = inputQuery.text.toString().trim()
        service.searchMovie(AppConstants.API_KEY, AppConstants.LANGUAGE, query, AppConstants.PAGE)
            .enqueue(object : Callback<ResponseMovie> {
                override fun onResponse(
                    call: Call<ResponseMovie>,
                    response: Response<ResponseMovie>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()?.movies
                        if (responseBody != null) {
                            adapter.setData(responseBody)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                    t.message
                }

            })
    }

}