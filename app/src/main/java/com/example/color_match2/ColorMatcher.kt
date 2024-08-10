package com.example.color_match2

class ColorMatcher(private val api: ColorAPI) {
    suspend fun getComplementaryColors(hex: String): List<Color> {
        return getColorScheme(hex, "complement", 2)
    }

    suspend fun getAnalogousColors(hex: String): List<Color> {
        return getColorScheme(hex, "analogic", 3)
    }

    suspend fun getTriadicColors(hex: String): List<Color> {
        return getColorScheme(hex, "triad", 3)
    }

    private suspend fun getColorScheme(hex: String, mode: String, count: Int): List<Color> {
        val response = api.getColorScheme(hex, mode, count)
        return response.colors.map { colorInfo ->
            Color(colorInfo.name.value, colorInfo.hex.value)
        }
    }
}