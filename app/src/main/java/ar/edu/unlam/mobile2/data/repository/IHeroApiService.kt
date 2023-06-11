package ar.edu.unlam.mobile2.data.repository

import ar.edu.unlam.mobile2.domain.hero.DataHero
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface IHeroApiService {

    @GET
    fun getHero(@Url idHero:String): Call<DataHero>
}