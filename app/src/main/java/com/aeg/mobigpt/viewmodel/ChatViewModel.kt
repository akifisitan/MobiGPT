package com.aeg.mobigpt.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aeg.mobigpt.api.RetrofitInstance
import com.aeg.mobigpt.model.ChatPayload
import com.aeg.mobigpt.model.Message
import com.aeg.mobigpt.model.ResponseMessage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    var job: Job? = null
    var chatMessage = MutableLiveData<Message>()
    var apiKey: String? = null

    fun createCompletion(model: String, messages: List<Message>) {
        val chatPayload = ChatPayload(model, messages)
        job = viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.createCompletion(chatPayload, token="Bearer $apiKey")
                if (response.isSuccessful) {
                    val responseContent = response.body()?.choices?.get(0)?.message?.content
                    val responseUsage = response.body()?.usage?.total_tokens
                    chatMessage.postValue(Message("bot", responseContent!!))
                }
            } catch (e: Exception) {
                val err = e.toString()
                Log.e("DEV", err, e)
            }
        }
        if (job!!.isCompleted) {
            job!!.cancel()
        }
    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }




}