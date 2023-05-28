package ar.edu.unlam.mobile2.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val api_key = "10160635333444116"
    private const val BASE_URL = "https://www.superheroapi.com/api/{$api_key}/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val superheroApiService: SuperheroApiService by lazy {
        retrofit.create(SuperheroApiService::class.java)
    }
}