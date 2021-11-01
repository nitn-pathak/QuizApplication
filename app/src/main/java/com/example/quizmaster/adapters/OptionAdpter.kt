package com.example.quizmaster.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizmaster.R
import com.example.quizmaster.models.Question


class OptionAdapter( val context:Context, val question : Question) :RecyclerView.Adapter<OptionAdapter.OptionViewHolder>(){


    private var options :List<String>  = listOf(question.option1, question.option2,question.option3,question.option4)


         inner class OptionViewHolder(itemview : View): RecyclerView.ViewHolder(itemview){
            val optionview = itemview.findViewById<TextView>(R.id.option_view)
              }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
            val view : View = LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)

               return OptionViewHolder(view)

                 }

        override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {



            holder.optionview.text = options[position]


            holder.itemView.setOnClickListener {

                //Toast.makeText(context,options[position],Toast.LENGTH_SHORT).show()
                question.useranswer = options[position]
                notifyDataSetChanged()
               }

              if(question.useranswer == options[position]){

                  holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)

               }else{
                  holder.itemView.setBackgroundResource(R.drawable.option_item_bg)

               }

                }


         override fun getItemCount(): Int {
            return options.size

              }



    }
