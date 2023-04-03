package com.jantzen.example.gamerelease.data.remote

import com.jantzen.example.gamerelease.data.model.ResponseToken

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query


// muss geändert werden

const val BASE_URL = "https://id.twitch.tv/oauth2/"
val client: OkHttpClient =
    OkHttpClient.Builder().addInterceptor { chain -> val newRequest: Request = chain.request().newBuilder()
        .build()
        chain.proceed(newRequest)
    }.build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @POST("token")
    suspend fun getToken(@Query("client_id") id : String = "wqtf67sczgqg3ptcura88lvj4cvio4",
                         @Query("client_secret") secret : String = "5ygpxmszngeue7fa9qvj7xr2ggf9xw" ,
                         @Query("grant_type") grant : String = "client_credentials"
    ) : ResponseToken
}
object TokenAPI {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}