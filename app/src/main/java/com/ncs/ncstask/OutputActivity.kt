package com.ncs.ncstask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ncs.ncstask.databinding.ActivityOutputBinding

class OutputActivity : AppCompatActivity() {
    val binding : ActivityOutputBinding by lazy {
        ActivityOutputBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()

    }

    fun init(){
        val outputExtra = intent.getStringExtra("OUTPUT")
        binding.textView.text = outputExtra
        binding.button.setOnClickListener(::sendBackToHome)

    }

    fun sendBackToHome(view: View?){
        startActivity(Intent(this, MainActivity::class.java))
    }

}