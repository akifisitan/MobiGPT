package com.aeg.mobigpt

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aeg.mobigpt.adapter.ViewPager2Adapter
import com.aeg.mobigpt.databinding.ActivityMainBinding
import com.aeg.mobigpt.viewmodel.ChatViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val layout = listOf("Chat", "Settings")
    private val viewModel = ChatViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE)
        val apiKey = sharedPreferences.getString("API_KEY", null)
        //val editor = sharedPreferences.edit()
        //editor.putString("API_KEY", apiKey)
        //editor.apply()
        if (apiKey == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        viewModel.apiKey = apiKey
        // Set ViewPager2's adapter & specify tab count
        val pagerAdapter2 = ViewPager2Adapter(supportFragmentManager, lifecycle, 2)
        binding.viewPager.adapter = pagerAdapter2
        // Attach the TabLayout to the ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) {
                tab: TabLayout.Tab, position: Int ->
                tab.text = layout[position]
        }.attach()
    }



}