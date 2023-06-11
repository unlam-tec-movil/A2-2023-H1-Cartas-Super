package ar.edu.unlam.mobile2.data.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HeroApiClient {

    /*
    acá se hardcodeó la api_key y no tendría que ser así.
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://superheroapi.com/api/10160635333444116/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: IHeroApiService = retrofit.create(IHeroApiService::class.java)

}