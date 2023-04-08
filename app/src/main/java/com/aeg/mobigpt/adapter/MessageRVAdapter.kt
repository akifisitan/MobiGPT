package com.aeg.mobigpt.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.aeg.mobigpt.ChatActivity
import com.aeg.mobigpt.R
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
            //txtViewReceivedMessage.text = message.content
            if (message.role != "user") {
                txtViewSentMessage.isVisible = false
                txtViewReceivedMessage.setBackgroundResource(R.drawable.received_message_layout)
                txtViewReceivedMessage.text = message.content
            } else {
                //row.setBackgroundColor(ContextCompat.getColor(ctx, R.color.teal_700))
                txtViewReceivedMessage.isVisible = false
                txtViewSentMessage.setBackgroundResource(R.drawable.sent_message_layout)
                txtViewSentMessage.text = message.content
            }
        }
    }
}


