package com.example.quizmaster.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizmaster.R
import com.example.quizmaster.ResultActivity
import com.example.quizmaster.adapters.OptionAdapter
import com.example.quizmaster.models.Question
import com.example.quizmaster.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_question2.*

class QuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question2)


          setupfirestore()
          setupeventlistners()
       }

         var index = 1
         var quizes : MutableList<Quiz>? = null
         var questions :MutableMap<String,Question>? = null


      fun setupeventlistners() {

           PreviousButton.setOnClickListener {

               index --
               bindviews()
             }


             Nextbutton.setOnClickListener {

                index ++
                bindviews()
               }

          SubmitButton.setOnClickListener {

                Log.d("FINALQUIZ",questions.toString())

              val intent = Intent(this, ResultActivity::class.java)

              val json:String = Gson().toJson(quizes!![0])                         // serialize
                intent.putExtra("QUIZ",json)
                 startActivity(intent)
          }
      }

                       fun setupfirestore(){

                           val firestore = FirebaseFirestore.getInstance()

                       val date =  intent.getStringExtra("DATE")

                        if(date!=null) {

                       firestore.collection("quizes").whereEqualTo("title",date)

                     .get().addOnSuccessListener {

                           if(it != null && !it.isEmpty) {
                               quizes = it.toObjects(Quiz::class.java)
                               questions = quizes!![0].questions
                               bindviews()
                           }
                    }
               }
          }



            fun bindviews(){

          PreviousButton.visibility= View.GONE
          SubmitButton.visibility= View.GONE
          Nextbutton.visibility= View.GONE

          if(index==1){                              // first question
              Nextbutton.visibility=View.VISIBLE
            }

          else if(index == questions!!.size){        //last question

              SubmitButton.visibility=View.VISIBLE
              PreviousButton.visibility= View.VISIBLE
           }
          else
           {                                        // middel question
               Nextbutton.visibility=View.VISIBLE
              PreviousButton.visibility= View.VISIBLE

           }


       val question = questions!!["question$index"]

          question?.let {

              descriptionView.text = it.description

              val optionAdapter = OptionAdapter(this,it)
              option_List.layoutManager= LinearLayoutManager(this)
              option_List.adapter=optionAdapter
              option_List.setHasFixedSize(true)
          }


       }

   }