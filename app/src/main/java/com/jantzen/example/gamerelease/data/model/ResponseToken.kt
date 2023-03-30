package com.jantzen.example.gamerelease.data.model

data class ResponseToken(val access_token: String,
                         val expires_in: Int,
                         val token_type: String){

}