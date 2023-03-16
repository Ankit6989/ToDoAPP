package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class UpdateCard : AppCompatActivity() {

     lateinit var database: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)

        database = DataBase.getDatabase(this)

        val updatedetails = findViewById<TextView>(R.id.updateDetails)
        val updatetask = findViewById<EditText>(R.id.updateTask)
        val updatepriority = findViewById<EditText>(R.id.updsatePriority)
        val updatebutton = findViewById<Button>(R.id.updateButton)
        val deletebutton = findViewById<Button>(R.id.deleteButton)



        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val priority = DataObject.getData(pos).priority
            updatetask.setText(title)
            updatepriority.setText(priority)

            deletebutton.setOnClickListener {
                DataObject.deleteData(pos)

                GlobalScope.launch {
                    database.dao().deleteTask(Entity(pos + 1,  updatetask.text.toString(),  updatepriority.text.toString()))
                }
                myIntent()
            }
        }

        updatebutton.setOnClickListener {
            DataObject.updateData(
                pos,
                updatetask.text.toString(),
                updatepriority.text.toString()
            )


            GlobalScope.launch {
                database.dao().updateTask(Entity(pos + 1,  updatetask.text.toString(),  updatepriority.text.toString()))
            }
                myIntent()
            }

        }


        fun myIntent() {
            val intent = Intent(this@UpdateCard, MainActivity::class.java)
            startActivity(intent)
        }

}





