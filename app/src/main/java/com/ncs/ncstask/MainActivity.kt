package com.ncs.ncstask

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ncs.ncstask.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    val TAG = this.javaClass.simpleName
    val sharedPreferences : SharedPreferences by lazy {
        this@MainActivity.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }
    val editor : SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }

    val counter : Int by lazy {
        sharedPreferences.getInt("COUNTER",0)
    }


    fun saveTextLocally(view : View?){
        if (!binding.input.text.toString().equals("")){
        val text = binding.input.text.toString().lowercase(Locale.ROOT)
        Log.d("TAG001", "Counter : ${counter+1}")
        val array = text.split(",").map { it.trim() }
        for (i in array){
            Log.d("TAG001", "String : $i")
        }

        val newCounter = counter +1
        editor.putString("INPUT$newCounter",text)
        editor.putInt("COUNTER",newCounter)
        val success = editor.commit()
        if (success){
            Toast.makeText(this, "Saved locally", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show()

        }}else {
            Toast.makeText(this, "Input can't be empty", Toast.LENGTH_SHORT).show()

        }

    }

    fun nextButtonClick(view: View?){
        startActivity(Intent(this, SearchActivity::class.java))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

    }


    
    fun init(){
        binding.buttonSave.setOnClickListener(::saveTextLocally)
        binding.buttonNext.setOnClickListener(::nextButtonClick)


    }
}