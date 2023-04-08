package com.aeg.mobigpt.api

import com.aeg.mobigpt.model.ChatPayload
import com.aeg.mobigpt.model.ResponseMessage
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface MobiGPTApi {

    @POST("v1/chat/completions")
    suspend fun createCompletion(
        @Body chatPayload: ChatPayload,
        @Header("Authorization") token: String,
        @Header("Content-Type") contentType: String = "application/json"
    ): Response<ResponseMessage>

    @GET("v1/usage?date=2023-04-09")
    suspend fun getUsage(
        @Header("Authorization") token: String,
        @Header("Content-Type") contentType: String = "application/json"
    ): Response<String>

}