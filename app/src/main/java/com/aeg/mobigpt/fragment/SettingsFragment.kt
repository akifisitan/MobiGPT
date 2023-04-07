package com.aeg.mobigpt.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.aeg.mobigpt.R
import com.aeg.mobigpt.databinding.FragmentSettingsBinding
import com.aeg.mobigpt.model.Message
import com.aeg.mobigpt.viewmodel.ChatViewModel


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChatViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.testBtn.setOnClickListener {
            binding.textView.text = ""
            binding.prgBar.isVisible = true
            val msg = binding.prompt.text.toString().trim()
            val message = Message("user", msg)
            val messageList = listOf(message)
            viewModel.createCompletion("gpt-3.5-turbo", messageList)
        }
        viewModel.chatMessage.observe(viewLifecycleOwner) { message ->
            binding.prgBar.isVisible = false
            binding.textView.text = message.content
        }

        return view
    }


}