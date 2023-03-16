package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.room.Database
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {

    lateinit var database: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

       database = DataBase.getDatabase(this)

        GlobalScope.launch {
            DataObject.listdata = database.dao().getTask() as MutableList<CardInfo>
        }


        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this@SplashScreen,MainActivity::class.java)
            startActivity(intent)
        },2000)

    }
}