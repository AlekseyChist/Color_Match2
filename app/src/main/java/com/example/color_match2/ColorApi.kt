package com.example.color_match2

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query

interface ColorAPI {
    @GET("scheme")
    suspend fun getColorScheme(
        @Query("hex") hex: String,
        @Query("mode") mode: String,
        @Query("count") count: Int
    ): ColorSchemeResponse
}