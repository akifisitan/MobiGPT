package com.aeg.mobigpt.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aeg.mobigpt.ChatActivity
import com.aeg.mobigpt.databinding.ChatRowLayoutBinding
import com.aeg.mobigpt.databinding.MessageRowLayoutBinding
import com.aeg.mobigpt.model.Chat
import com.aeg.mobigpt.model.Message

class MessageRVAdapter(private val ctx: Context, private val messageList: List<Message>)
    : RecyclerView.Adapter<MessageRVAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(val binding: MessageRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
            // data comes here
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            MessageRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.binding.apply {
            val message = messageList[position]
            // Set the variables here
            //title.text = message

        }
    }
}


