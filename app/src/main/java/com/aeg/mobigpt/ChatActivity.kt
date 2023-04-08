package com.aeg.mobigpt

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aeg.mobigpt.adapter.MessageRVAdapter
import com.aeg.mobigpt.databinding.ActivityChatBinding
import com.aeg.mobigpt.model.Chat
import com.aeg.mobigpt.model.Message
import com.aeg.mobigpt.model.MessagePayload
import com.aeg.mobigpt.viewmodel.ChatViewModel

class ChatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChatBinding
    private val viewModel: ChatViewModel by viewModels()
    private var apiKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // Check if there is an API key stored in SharedPreferences

        val sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        apiKey = sharedPreferences.getString("API_KEY", null)
        // If there is no API key, redirect to the login screen
        if (apiKey == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        //val apiKey = "123123123"
        // Set the toolbar
        binding.chatToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))

        // Get the selected chat from the intent
        val chat = intent.getSerializableExtra("selectedChat") as Chat
        val messageAdapter = MessageRVAdapter(this, chat.messages)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = messageAdapter

        // Scroll to the bottom of the recycler view when a new message is added
        messageAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                binding.recyclerView.scrollToPosition(positionStart);
            }
        })

        // Send a message to the the api
        binding.btnAsk.setOnClickListener {
            val message = Message("user", binding.editTxtEnterMessage.text.trim().toString(),
                System.currentTimeMillis())
            chat.messages.add(message)
            messageAdapter.notifyItemInserted(chat.messages.size - 1)
            binding.editTxtEnterMessage.text.clear()
            val listPayload: MutableList<MessagePayload> = mutableListOf()
            chat.messages.forEach {
                val payload = MessagePayload(it.role, it.content)
                listPayload.add(payload)
            }
            binding.btnAsk.isEnabled = false
            Log.i("DEV", "Sending payload: $listPayload")
            Log.i("DEV", "Sending payload: $apiKey")
            viewModel.createCompletion(apiKey=apiKey!!, messages=listPayload)
        }

        // Observe the response from the api
        viewModel.responseMessage.observe(this) {
            val message = Message("system", it, System.currentTimeMillis())
            chat.messages.add(message)
            messageAdapter.notifyItemInserted(chat.messages.size - 1)
            binding.btnAsk.isEnabled = true
        }


    }
}