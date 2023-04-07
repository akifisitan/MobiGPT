package com.aeg.mobigpt.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Chat(
    @SerializedName("title")
    var title: String,
    @SerializedName("messages")
    var messages: List<Message> = listOf()
) : Serializable