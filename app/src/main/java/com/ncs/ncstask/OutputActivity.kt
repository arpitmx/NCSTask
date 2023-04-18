package com.ncs.ncstask

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        binding.copy.setOnClickListener{
           copy()
        }

        binding.textView.setOnClickListener{
           copy()
        }

    }

    fun copy(){
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", binding.textView.text.toString())
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }
    fun sendBackToHome(view: View?){
        startActivity(Intent(this, MainActivity::class.java))
    }

}