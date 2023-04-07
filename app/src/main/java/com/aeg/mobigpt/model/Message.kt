package com.aeg.mobigpt.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Message(
    @SerializedName("role")
    val role: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("timestamp")
    val timestamp: String = "default",
    @SerializedName("sender")
    val sender: String = "default"
) : Serializable
