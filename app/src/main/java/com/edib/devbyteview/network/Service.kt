package com.edib.devbyteview.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

object DevByteNetwork{

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val devBytes = retrofit.create(DevByteService::class.java)
}

interface DevByteService {

    @GET("devbytes")
    suspend fun getPlaylist(): NetworkVideoContainer

}
