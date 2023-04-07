package com.aeg.mobigpt

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.aeg.mobigpt.adapter.ChatRVAdapter
import com.aeg.mobigpt.adapter.MessageRVAdapter
import com.aeg.mobigpt.databinding.ActivityChatBinding
import com.aeg.mobigpt.databinding.ActivityMainBinding
import com.aeg.mobigpt.model.Chat
import com.aeg.mobigpt.model.Message

class ChatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.chatToolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))
        val chat = intent.getSerializableExtra("selectedChat") as Chat
        val messageAdapter = MessageRVAdapter(this, chat.messages)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = messageAdapter
    }
}