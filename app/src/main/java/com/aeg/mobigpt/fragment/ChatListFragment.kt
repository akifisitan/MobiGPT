package com.aeg.mobigpt.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
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
        activity?.findViewById<View>(R.id.newChatBtn)?.setOnClickListener {
            NewChatDialog().show(childFragmentManager, NewChatDialog.TAG)
        }

        //binding.prgBar.isVisible = true
        //binding.recView.isVisible = false
        // add some sample chats
        val messages1 = listOf(
            Message("Hello", "12:00"),
            Message("Hi", "12:01"),
            Message("How are you?", "12:02"),
            Message("I'm fine, thanks", "12:03"),
            Message("What about you?", "12:04"),
            Message("I'm fine too", "12:05")
        )
        val messages2 = listOf(
            Message("Hello", "12:00"),
            Message("Hi", "12:01"),
            Message("How are you?", "12:02"),
            Message("I'm fine, thanks", "12:03"),
            Message("What about you?", "12:04"),
            Message("I'm fine too", "12:05")
        )
        val chat1 = Chat("Chat 1", messages1)
        val chat2 = Chat("Chat 2", messages2)
        val chatList: List<Chat> = listOf(chat1, chat2)
        val chatAdapter = ChatRVAdapter(requireContext(), chatList)
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = chatAdapter

        return view
    }


}