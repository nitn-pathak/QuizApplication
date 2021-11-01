package com.example.quizmaster.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizmaster.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

          firebaseAuth = FirebaseAuth.getInstance()


          haveAccount.setOnClickListener {
             val intent = Intent(this, Login::class.java)
            startActivity(intent)
                finish()
                }

               creatacc.setOnClickListener {
                   registeruser()

                 }


            }



    private fun registeruser(){


        val email = email.text.toString()
        val Password = Password.text.toString()
        val confirmpassword= confirmpassword.text.toString()




        if(email.isBlank()|| Password.isBlank()||confirmpassword.isBlank()){

            Toast.makeText(this,"fields not be empty",Toast.LENGTH_LONG).show()
            return
        }


        if(Password!=confirmpassword){

            Toast.makeText(this,"password and confirmpasword donot match ",Toast.LENGTH_LONG).show()
            return

        }



        firebaseAuth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener(this){

            if(it.isSuccessful){
                Toast.makeText(this,"succesfull",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()

            }
        }







    }

}