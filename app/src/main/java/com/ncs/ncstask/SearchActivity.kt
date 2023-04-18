package com.ncs.ncstask

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ncs.ncstask.databinding.ActivitySearchBinding
import java.util.Locale


class SearchActivity : AppCompatActivity() {

    lateinit var binding : ActivitySearchBinding
    val TAG = this.javaClass.simpleName
    val sharedPreferences : SharedPreferences by lazy {
        this@SearchActivity.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    val counter : Int by lazy {
        sharedPreferences.getInt("COUNTER",0)
    }

    lateinit var currentList : ArrayList<String>


    lateinit var lvAdapter : ArrayAdapter<String>
   val collection:ArrayList<String> by lazy {
        extractData()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()


    }

   val textWatcher = object : TextWatcher {
       override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
       }

       override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      
       }

       override fun afterTextChanged(editable: Editable?) {
           Log.d(TAG, "afterTextChanged: ${editable.toString()}")
           val input = editable.toString().toLowerCase(Locale.ROOT)
           if (input == ""){
                resetList()
           }else{
               getMatches(collection,input)
           }
       }

   }


    fun extractData() : ArrayList<String>{
        val collect = ArrayList<String>()
        for (c in 1..counter){
            val strings = sharedPreferences.getString("INPUT${c}","-1")!!.split(",")
            for (i in strings){
                collect.add(i.trim())
                Log.d(TAG, "Array element : $i")
            }
        }

        return collect
    }

    fun getMatches(collection:ArrayList<String>, input : String){
        val tempCollection : ArrayList<String> = ArrayList()

        for (i in collection){
            val index = i.indexOf(input)
            if (index != -1) {
                val statement = i.substring(index)
                tempCollection.add(statement)
            }
        }

        lvAdapter.clear()
        lvAdapter.addAll(tempCollection)
        currentList = tempCollection
        lvAdapter.notifyDataSetChanged()
    }

    fun resetList(){
        val temp = extractData()
        lvAdapter.clear()
        lvAdapter.addAll(temp)
        currentList = temp
        lvAdapter.notifyDataSetChanged()
    }

    fun init(){
        currentList = extractData()
        lvAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,currentList)
        binding.listView.adapter = lvAdapter
        binding.searchbar.addTextChangedListener(textWatcher)

        binding.listView.setOnItemClickListener{ parent, view, position, id ->
            //Toast.makeText(this, "$position", Toast.LENGTH_SHORT).show()
            val currentString = currentList[position]
            val intent = Intent(this, OutputActivity::class.java)
            intent.putExtra("OUTPUT", currentString)
            startActivity(intent)
        }


    }

}