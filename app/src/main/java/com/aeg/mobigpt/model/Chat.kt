package com.aeg.mobigpt.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Chat(
    var title: String,
    var messages: MutableList<Message> = mutableListOf()
): Serializable