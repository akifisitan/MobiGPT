package com.aeg.mobigpt.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aeg.mobigpt.R
import com.aeg.mobigpt.adapter.ChatRVAdapter
import com.aeg.mobigpt.databinding.FragmentChatListBinding
import com.aeg.mobigpt.dialog.NewChatDialog
import com.aeg.mobigpt.model.Chat
import com.aeg.mobigpt.model.Message


class ChatListFragment : Fragment() {
    private var _binding: FragmentChatListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        val view = binding.root

        // Add an example chat
        Log.i("DEV", System.currentTimeMillis().toString())
        val exampleMessageList = mutableListOf(
            Message("user", "Hello", System.currentTimeMillis()),
            Message("system", "Hello, how can I help you today?", System.currentTimeMillis()),
            Message("user", "What's 10+10?", System.currentTimeMillis()),
            Message("user", "20", System.currentTimeMillis()))

        val exampleChat = Chat("Example Chat", exampleMessageList)
        val chatList: MutableList<Chat> = mutableListOf(exampleChat)

        // Set up the recycler view
        val chatAdapter = ChatRVAdapter(requireContext(), chatList)
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = chatAdapter

        activity?.findViewById<View>(R.id.newChatBtn)?.setOnClickListener {
            // NewChatDialog().show(childFragmentManager, NewChatDialog.TAG)
            chatList.add(Chat("New Chat", mutableListOf()))
            chatAdapter.notifyItemInserted(chatList.size - 1)
        }

        return view
    }


}