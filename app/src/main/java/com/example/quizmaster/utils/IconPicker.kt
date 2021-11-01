package com.example.quizmaster.utils

import com.example.quizmaster.R

object IconPicker {

    val icon = arrayOf(

        R.drawable.ic_image1,
        R.drawable.ic_image2,
        R.drawable.ic_image3,
        R.drawable.ic_image4,
        R.drawable.ic_image5,
        R.drawable.ic_image6,
        R.drawable.ic_image7,
        R.drawable.ic_image8,


        )

    var currenticon = 0

    fun geticon ():Int{

        currenticon = ( currenticon+1)% icon.size

        return icon[currenticon]
    }


}