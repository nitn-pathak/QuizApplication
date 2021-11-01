package com.example.quizmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizmaster.activities.Intro
import com.example.quizmaster.activities.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    lateinit var  firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        firebaseAuth = FirebaseAuth.getInstance()

        emailid.text = firebaseAuth.currentUser?.email

        Logoutbtn.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this,Intro::class.java)
             startActivity(intent)


        }


       }
}