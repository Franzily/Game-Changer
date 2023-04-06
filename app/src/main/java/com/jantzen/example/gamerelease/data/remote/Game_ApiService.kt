package com.jantzen.example.gamerelease.data.remote



import com.jantzen.example.gamerelease.data.model.Game
import com.jantzen.example.gamerelease.data.model.Game_Alternative
import com.jantzen.example.gamerelease.data.model.Game_Cover
import com.jantzen.example.gamerelease.data.model.Game_Release
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST


 //var token = Repository(TokenAPI).getToken()

// muss geändert werden
const val BASE_URL_RELEASE = "https://api.igdb.com/v4/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL_RELEASE)
    .build()

// name muss geändert werden
interface Game_ApiService {


    // muss angepasst werden
    //@Headers( "client_id: wqtf67sczgqg3ptcura88lvj4cvio4", "Authorization: Bearer g6q60hrgdbrgk8h0c9smra4pctcbhf" )
    @POST("release_dates")
    suspend fun getRelease(
        @Header ("Client-ID") id: String = "wqtf67sczgqg3ptcura88lvj4cvio4",
        @Header ("Authorization") auth: String,
        @Body fields: String = "fields name,date"
    ): List<Game_Release>

    @POST("alternative_names")
    suspend fun getName(
        @Header ("Client-ID") id: String = "wqtf67sczgqg3ptcura88lvj4cvio4",
        @Header ("Authorization") auth: String,
        @Body fields: String = "fields name,date"
    ): List<Game_Alternative>

    @POST("games")
    suspend fun getGame(
        @Header ("Client-ID") id: String = "wqtf67sczgqg3ptcura88lvj4cvio4",
        @Header ("Authorization") auth: String,
        @Body fields: String = "fields name,first_release_date,cover,id,game_modes,storyline; limit 10"
    ): List<Game>

    @POST("covers")
    suspend fun getCover(
        @Header ("Client-ID") id: String = "wqtf67sczgqg3ptcura88lvj4cvio4",
        @Header ("Authorization") auth: String,
        @Body fields: String  //TODO ID einfügen
    ): List<Game_Cover>


}

// namen müssen geändert werden
object GameAPI {
    val retrofitService: Game_ApiService by lazy { retrofit.create(Game_ApiService::class.java) }
}