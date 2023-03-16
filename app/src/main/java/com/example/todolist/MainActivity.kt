package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Database
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.todolist.NotesAdapter as NotesAdapter


class MainActivity : AppCompatActivity() {

    lateinit var database: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = DataBase.getDatabase(this)



        val recyclerview = findViewById<RecyclerView>(R.id.recycleView)
        val yourtask = findViewById<TextView>(R.id.yourTask)
        val layout = findViewById<LinearLayout>(R.id.linearLayout)
        val deleteallbutton = findViewById<Button>(R.id.deleteAllButton)
        val addbutton = findViewById<Button>(R.id.addButton)
        val adapter : NotesAdapter

        fun setRecycler() {
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerview.adapter = NotesAdapter(DataObject.getAllData())
        }

        addbutton.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }

        deleteallbutton.setOnClickListener {

            DataObject.deleteAll()

           GlobalScope.launch {
                database.dao().deleteAll()

            }

            setRecycler()

        }
        setRecycler()

    }


}


  
