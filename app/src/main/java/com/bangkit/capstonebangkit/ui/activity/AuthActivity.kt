package com.bangkit.capstonebangkit.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.capstonebangkit.R
import com.bangkit.capstonebangkit.databinding.ActivityAuthBinding
import com.bangkit.capstonebangkit.databinding.ActivityWelcomeBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }

            val auth = intent.getStringExtra("auth")
            if (auth == "login") {
                tvTitle.text = "Login"
                tvContent.text = "Forgot your password? It’s your problem then"
                btnRegis.text = "Login"
                btnRegis.setOnClickListener {
                    startActivity(Intent(this@AuthActivity, MainActivity::class.java))
                    finish()
                }
            } else {
                tvTitle.text = "Register"
                tvContent.text = "Already have account?click here"
                tvContent.setOnClickListener {
                    startActivity(Intent(this@AuthActivity, AuthActivity::class.java).apply {
                        putExtra("auth", "login")
                    })
                }
                btnRegis.setOnClickListener {
                    startActivity(Intent(this@AuthActivity, AuthActivity::class.java).apply {
                        putExtra("auth", "login")
                    })
                }
            }
        }

    }
}