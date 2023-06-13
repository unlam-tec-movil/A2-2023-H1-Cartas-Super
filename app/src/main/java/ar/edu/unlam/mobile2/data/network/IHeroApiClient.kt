package ar.edu.unlam.mobile2.data.network

import ar.edu.unlam.mobile2.domain.hero.DataHero
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface IHeroApiClient {

    @GET
    suspend fun getHero(@Url idHero:String): Call<DataHero>
    @GET
    suspend fun getHeroResponse(@Url idHero:String): Response<DataHero>
}