package com.example.color_match2

import com.google.gson.annotations.SerializedName

data class ColorSchemeResponse(
    val mode: String,
    val count: String,
    val colors: List<ColorInfo>,
    val seed: ColorInfo
)

data class ColorInfo(
    val hex: HexInfo,
    val rgb: RgbInfo,
    val hsl: HslInfo,
    val name: NameInfo
)

data class HexInfo(
    val value: String,
    val clean: String
)

data class RgbInfo(
    val r: Int,
    val g: Int,
    val b: Int,
    val value: String
)

data class HslInfo(
    val h: Int,
    val s: Int,
    val l: Int,
    val value: String
)

data class NameInfo(
    val value: String,
    @SerializedName("closest_named_hex") val closestNamedHex: String,
    @SerializedName("exact_match_name") val exactMatchName: Boolean,
    val distance: Int
)