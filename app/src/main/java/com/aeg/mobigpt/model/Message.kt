package com.aeg.mobigpt.model
import java.io.Serializable

data class Message(
    val role: String,
    val content: String,
    val timestamp: Long,
): Serializable
