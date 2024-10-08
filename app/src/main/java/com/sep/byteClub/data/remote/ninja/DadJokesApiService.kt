package com.sep.byteClub.data.remote.ninja

import com.sep.byteClub.data.model.response.dadJokes.DadJokeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface DadJokesApiService {

    @Headers("X-Api-Key: RTmDg/ButRxwV6UN4nP6Ww==uv2XbjGFmPczlxiZ")
    @GET("v1/dadjokes")
    suspend fun getJoke(): Response<List<DadJokeDto>>

}