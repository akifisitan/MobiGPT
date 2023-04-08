package com.aeg.mobigpt.model

import com.google.gson.annotations.SerializedName

data class ResponseMessage(
    @SerializedName("id")
    val id: String,
    @SerializedName("object")
    val `object`: String,
    @SerializedName("created")
    val created: Int,
    @SerializedName("choices")
    val choices: List<ResponseChoice>,
    @SerializedName("usage")
    val usage: ResponseUsage,
)
