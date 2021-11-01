package com.example.quizmaster.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.activities.QuestionActivity
import com.example.quizmaster.R

import com.example.quizmaster.models.Quiz
import com.example.quizmaster.utils.IconPicker
import com.example.quizmaster.utils.colourpicker


class QuizAdapter( var context:Context, var quizes : List<Quiz>) :RecyclerView.Adapter<QuizAdapter.QuizViewHolder>(){


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {

           val view:View  = LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)

            return QuizViewHolder(view)
          }

        override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {

               holder.textViewtitle.text = quizes[position].title
             holder.cardContainer.setCardBackgroundColor(Color.parseColor(colourpicker.getcolor()))
             holder.quizicon.setImageResource(IconPicker.geticon())


            holder.itemView.setOnClickListener{
                  Toast.makeText(context,quizes[position].title,Toast.LENGTH_SHORT).show()

                val intent = Intent(context, QuestionActivity::class.java)
                intent.putExtra("DATE",quizes[position].title)
                 context.startActivity(intent)
            }


         }

       override fun getItemCount(): Int {

           return quizes.size

        }


       inner class QuizViewHolder(itemview :View):RecyclerView.ViewHolder(itemview){

           var textViewtitle:TextView = itemview.findViewById(R.id.quiztitle)
           var quizicon:ImageView = itemview.findViewById(R.id.quizicon)
           var cardContainer:CardView = itemview.findViewById(R.id.cardcontainer)


         }



}