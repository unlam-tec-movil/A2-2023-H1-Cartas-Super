package ar.edu.unlam.mobile2.data

import retrofit2.Response
import retrofit2.http.GET

interface KittyApi {
    //@GET("/v1/images/search?limit=3")
    @GET("character-id/image")
    suspend fun getKitties(): Response<List<KittyApiModel>>
}
