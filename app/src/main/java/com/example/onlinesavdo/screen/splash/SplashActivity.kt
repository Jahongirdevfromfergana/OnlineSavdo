package com.example.onlinesavdo.screen.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlinesavdo.R
import com.example.onlinesavdo.databinding.ActivitySplashBinding
import com.example.onlinesavdo.screen.MainActivity

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animationView.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
        }, 3000)


    }
}