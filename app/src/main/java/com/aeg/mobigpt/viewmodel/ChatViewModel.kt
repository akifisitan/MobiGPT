package com.aeg.mobigpt.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aeg.mobigpt.api.RetrofitInstance
import com.aeg.mobigpt.model.ChatPayload
import com.aeg.mobigpt.model.Message
import com.aeg.mobigpt.model.MessagePayload
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    var job: Job? = null
    // var chatMessage = MutableLiveData<Message>()
    var responseMessage = MutableLiveData<String>()


    fun createCompletion(apiKey: String, model: String = "gpt-3.5-turbo", messages: List<MessagePayload>) {
        val chatPayload = ChatPayload(model, messages)
        job = viewModelScope.launch {
            try {
                Log.i("DEV", "ChatPayload: $chatPayload")
                val response = RetrofitInstance.api.createCompletion(chatPayload, "Bearer $apiKey")
                if (response.isSuccessful) {
                    val responseContent = response.body()?.choices?.get(0)?.message?.content
                    if (responseContent != null) {
                        responseMessage.postValue(responseContent.toString())
                    } else {
                        responseMessage.postValue("Error: No response content")
                    }
                }
                else {
                    responseMessage.postValue("Error: ${response.code()}")
                }
                Log.i("DEV", "Response: ${response.body()}")
            } catch (e: Exception) {
                val err = e.toString()
                responseMessage.postValue(err)
                Log.e("DEV", err, e)
            }
        }
        if (job!!.isCompleted) {
            job!!.cancel()
        }
    }

    fun getUsage() {
        job = viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getUsage("Bearer 123")
                if (response.isSuccessful) {
                    Log.i("DEV", "Response: ${response.body()}")
                    responseMessage.postValue(response.body())
                }
                else {
                    Log.i("DEV", "Response: ${response.code()}")
                    responseMessage.postValue("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                val err = e.toString()
                Log.e("DEV", err, e)
                responseMessage.postValue(err)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }




}