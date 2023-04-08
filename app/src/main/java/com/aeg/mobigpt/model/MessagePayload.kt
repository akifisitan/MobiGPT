package com.aeg.mobigpt.model

import com.google.gson.annotations.SerializedName

data class MessagePayload(

    @SerializedName("role")
    val role: String,

    @SerializedName("content")
    val content: String
)