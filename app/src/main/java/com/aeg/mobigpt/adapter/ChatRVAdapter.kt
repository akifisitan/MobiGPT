package com.aeg.mobigpt.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aeg.mobigpt.ChatActivity
import com.aeg.mobigpt.databinding.ChatRowLayoutBinding
import com.aeg.mobigpt.model.Chat

class ChatRVAdapter(private val ctx: Context, private val chatList: List<Chat>)
    : RecyclerView.Adapter<ChatRVAdapter.ChatViewHolder>() {

    inner class ChatViewHolder(val binding: ChatRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
            // data comes here
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        return ChatViewHolder(
            ChatRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = chatList.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.binding.apply {
            val chat = chatList[position]
            title.text = chat.title

            // Set the onclick listener
            rowLayout.setOnClickListener {
                val i = Intent(ctx, ChatActivity::class.java)
                i.putExtra("selectedChat", chat)
                ctx.startActivity(i)
            }
        }
    }
}


