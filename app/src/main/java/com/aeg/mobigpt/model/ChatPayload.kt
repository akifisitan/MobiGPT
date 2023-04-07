package com.aeg.mobigpt.model

import com.google.gson.annotations.SerializedName

data class ChatPayload(
    @SerializedName("model")
    val model: String,
    @SerializedName("messages")
    val messages: List<Message>,
)
