package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Database
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    lateinit var database: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        database = DataBase.getDatabase(this)




        val enterdetails = findViewById<TextView>(R.id.enterDetails)
        val entertask = findViewById<EditText>(R.id.editTask)
        val enterprioroty = findViewById<EditText>(R.id.editPriority)
        val enterbutton = findViewById<Button>(R.id.saveButton)



        enterbutton.setOnClickListener {

            if (entertask.text.toString().trim { it <= ' ' }.isNotEmpty()
                && enterprioroty.text.toString().trim { it <= ' ' }.isNotEmpty()

            ) {
                var title = entertask.text.toString()
                var priority = enterprioroty.text.toString()
                DataObject.setData(title, priority)


               GlobalScope.launch {
                    database.dao().insertTask(Entity(0,title,priority))
                }


            }
            else
            {
                Toast.makeText(applicationContext,"Task and Priority cannot be empty",Toast.LENGTH_LONG).show()
            }

                val intent = Intent(this@SecondActivity, MainActivity::class.java)
                startActivity(intent)

            }

        }


    }


