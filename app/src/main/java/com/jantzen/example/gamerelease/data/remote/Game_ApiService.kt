package com.jantzen.example.gamerelease.data.remote



import com.jantzen.example.gamerelease.data.model.GamesResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "aeb1018d19b49a20d43f475b49d396897085e9dd"
const val FORMAT = "json"
const val BASE_URL = "https://www.giantbomb.com/api/"

val client: OkHttpClient =
    OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
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

interface Game_ApiService {
    @GET("games")
    suspend fun getGame(
       // @Query ("filter") filter: String,
        @Query ("api key") key: String = API_KEY,
        @Query ("format") format: String = FORMAT,
        @Query ("sort") sort: String = "original_release_date:desc"
        //@Query ("limit") limit: Int = 100
    ): GamesResult
    @GET("search")
    suspend fun getSearchGame(
        @Query ("term") term:String,
        @Query ("name") name: String
    ):GamesResult
}

object GameAPI {
    val retrofitService: Game_ApiService by lazy { retrofit.create(Game_ApiService::class.java) }
}