package com.jantzen.example.gamerelease.data.remote



import com.jantzen.example.gamerelease.data.model.ResponseToken
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.POST



// muss geändert werden
const val BASE_URL = "https://id.twitch.tv/oauth2/token?client_id=wqtf67sczgqg3ptcura88lvj4cvio4&client_secret=5ygpxmszngeue7fa9qvj7xr2ggf9xw&grant_type=client_credentials"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

// name muss geändert werden
interface ApiService {

    // muss angepasst werden
    @POST("")
    suspend fun getToken(): ResponseToken
}

// namen müssen geändert werden
object TokenAPI {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}