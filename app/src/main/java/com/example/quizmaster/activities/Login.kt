package com.example.quizmaster.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizmaster.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

             firebaseAuth = FirebaseAuth.getInstance()

             Not_haveaccount.setOnClickListener {
            val intent = Intent(this, Register::class.java)        // Intent
                 startActivity(intent)
                   }

              loginbutton.setOnClickListener {
                  login()

                         }
                    }


               private fun login(){

                  val email = EmailAddress2.text.toString()
                   val password= Password2.text.toString()


                   if(email.isBlank()||password.isBlank()){

                       Toast.makeText(this,"Email and Password can not be Empty ",Toast.LENGTH_SHORT).show()
                                   return
                            }



                   firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){

                    if(it.isSuccessful){
                        //code
                        Toast.makeText(this,"Login Succesfull",Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                          }

                    else

                          {
                           Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
                           }
                        }
                                }



}