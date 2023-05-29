package ar.edu.unlam.mobile2.data

import ar.edu.unlam.mobile2.domain.hero.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroApiService {
    @GET("{id}/image")
    suspend fun getHeroImage(@Path("id") id: String): Response<Image>
}
