package com.jantzen.example.gamerelease.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(val medium_url: String?,
                val small_url: String?,
                val super_url: String?,
                val original_url: String?):Parcelable
