package com.aeg.mobigpt.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        // add some sample chats
        val messages1 = mutableListOf(
            Message("system", "hello", 12313),
            Message("user", "my name is", 123213),
            Message("system", "my name is", 123213),
            Message("user", "my name is", 123213),
            Message("user", "my name is", 123213))

        val chat1 = Chat("Chat 1", messages1)
        val chatList: List<Chat> = listOf(chat1)
        val chatAdapter = ChatRVAdapter(requireContext(), chatList)
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = chatAdapter

        return view
    }


}