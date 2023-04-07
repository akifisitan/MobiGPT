package com.aeg.mobigpt

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aeg.mobigpt.databinding.ActivityLoginBinding
import com.aeg.mobigpt.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.apiKeyBtn.setOnClickListener {
            val apiKey = binding.editTextApiKey.text.toString()
            editor.putString("API_KEY", apiKey)
            editor.apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}