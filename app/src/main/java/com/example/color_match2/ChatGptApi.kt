package com.example.color_match2

import retrofit2.http.Body
import retrofit2.http.POST

interface ChatGptApi {
    @POST("v1/images/generations")
    suspend fun generateImage(@Body request: ImageGenerationRequest): ImageGenerationResponse
}

data class ImageGenerationRequest(
    val prompt: String,
    val n: Int = 1,
    val size: String = "1024x1024"
)

data class ImageGenerationResponse(
    val data: List<ImageUrl>
)

data class ImageUrl(
    val url: String
)