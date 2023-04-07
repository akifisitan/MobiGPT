package com.aeg.mobigpt.model

import com.google.gson.annotations.SerializedName

data class ResponseChoice (
    @SerializedName("index")
    val index: Int,
    @SerializedName("message")
    val message: Message,
    @SerializedName("finish_reason")
    val finish_reason: String,
)
