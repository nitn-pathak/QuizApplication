package com.example.quizmaster.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.quizmaster.R
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class Intro : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

      val button = findViewById<Button>(R.id.buttonn)

        val auth = FirebaseAuth.getInstance()

            if(auth.currentUser!=null){
                Toast.makeText( this,"user is alredy logged in", Toast.LENGTH_SHORT).show()
                redirect("MAIN")
                }


              button.setOnClickListener {
               redirect("LOGIN")
                    }

    }


    private fun redirect(name: String) {
           val intent = when (name) {

            "LOGIN" -> Intent(this, Login::class.java)
            "MAIN" -> Intent(this, MainActivity::class.java)

              else -> throw Exception(" No path exist")
             }

              startActivity(intent)
                 finish()
          }

       }

