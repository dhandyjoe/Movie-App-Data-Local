package com.example.movieapp.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityLoginBinding
import com.example.movieapp.utils.UserPreferences

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userPreferences = UserPreferences(this)

        binding.btnLogin.setOnClickListener {
            if (binding.etUsername.text.isNullOrEmpty()) {
                binding.etUsername.error = "Please input Username"
            } else {
                userPreferences.setNameUser(binding.etUsername.text.toString())
                userPreferences.setStatusUser(true)

                val result = Intent()
                setResult(Activity.RESULT_OK, result)
                finish()
            }
        }
    }
}