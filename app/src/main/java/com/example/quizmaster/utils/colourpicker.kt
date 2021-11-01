package com.example.quizmaster.utils

import com.example.quizmaster.R

object colourpicker {


          val color = arrayOf(

              "#f55442",
              "#f57e42",
              "#e942f5",
              "#f54263",
              "#4278f5",
              "#42b3f5",



              )

      var currentcolor = 0

    fun getcolor(): String {
        currentcolor= (currentcolor + 1) % color.size

        return color[currentcolor]
    }


}