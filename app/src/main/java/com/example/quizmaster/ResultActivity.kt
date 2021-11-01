package com.example.quizmaster

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.example.quizmaster.models.Quiz
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    lateinit var  quiz: Quiz


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setupviews()


       }

       fun setupviews(){

            val quizData = intent.getStringExtra("QUIZ")

              Gson().fromJson(quizData,Quiz::class.java)           // Deseraialize

          calculateScore()

           setanswer()

           }

       fun setanswer() {

          val builder = StringBuilder(" ")

          for( entry in quiz.questions.entries){

              val question = entry.value
              builder.append("<font color '#18206F'> <b> Question: ${question.description} </b> </font> <br/> <br/>")
              builder.append("<font color '#18206F'>  Answer: ${question.answer}  </font> <br/> <br/>")
              }
       if( Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){


           answerView.text = Html.fromHtml(builder.toString(),Html.FROM_HTML_MODE_COMPACT)

     }else{
           answerView.text = Html.fromHtml(builder.toString());

        }


        }




    fun calculateScore() {

          var score = 0

          for( entry in quiz.questions.entries){
                 val question  = entry.value

              if(question.answer==question.useranswer){
                  score = score+10
                 }
               }
         Yourscore.text = "Your Score :$score"

        }


}