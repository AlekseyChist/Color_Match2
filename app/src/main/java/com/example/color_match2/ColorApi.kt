package com.example.color_match2

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

data class ColorSchemeResponse(
    val mode: String,
    val count: Int,
    val colors: List<String>,
    val seed: ColorInfo
)

data class ColorInfo(
    val hex: ColorHex,
    val rgb: ColorRGB,
    val hsl: ColorHSL,
    val name: ColorName
)

data class ColorHex(val value: String, val clean: String)
data class ColorRGB(val r: Int, val g: Int, val b: Int)
data class ColorHSL(val h: Int, val s: Int, val l: Int)
data class ColorName(val value: String, val closest_named_hex: String, val exact_match_name: Boolean)