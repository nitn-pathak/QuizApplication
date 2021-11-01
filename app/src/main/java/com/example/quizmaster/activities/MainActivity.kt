package com.example.quizmaster.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizmaster.ProfileActivity

import com.example.quizmaster.R
import com.example.quizmaster.adapters.QuizAdapter
import com.example.quizmaster.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore


import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {



    lateinit var adapter: QuizAdapter
    lateinit var firestore: FirebaseFirestore
    var quizlist = mutableListOf<Quiz>()
    lateinit var  actionBarDrawerToggle: ActionBarDrawerToggle


      override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

           dummydata()
           setUpViews()
      }


      private  fun dummydata(){

          quizlist.add(Quiz("11-2-2021","11-2-2021"))
          quizlist.add(Quiz("12/2/2021","12-2-2021"))
          quizlist.add(Quiz("13/2/2021","13-2-2021"))
          quizlist.add(Quiz("14/2/2021","14-2-2021"))
          quizlist.add(Quiz("15/2/2021","15-2-2021"))
          quizlist.add(Quiz("11-2-2021","11-2-2021"))
          quizlist.add(Quiz("12/2/2021","12-2-2021"))
          quizlist.add(Quiz("13/2/2021","13-2-2021"))
          quizlist.add(Quiz("14/2/2021","14-2-2021"))
          quizlist.add(Quiz("15/2/2021","15-2-2021"))
          quizlist.add(Quiz("11-2-2021","11-2-2021"))
          quizlist.add(Quiz("12/2/2021","12-2-2021"))
          quizlist.add(Quiz("13/2/2021","13-2-2021"))
          quizlist.add(Quiz("14/2/2021","14-2-2021"))
          quizlist.add(Quiz("15/2/2021","15-2-2021"))

      }


         fun setUpViews(){

                setuprecyclerview()
                setupfirestore()
                 setupdrawerlayout()
                 setuprecyclerview()
               setupdatepicker()

               }

        fun setupdatepicker(){

         datepickerbtn.setOnClickListener {

             val datepicker = MaterialDatePicker.Builder.datePicker().build()

              datepicker.show(supportFragmentManager,"DatePicker")

             datepicker.addOnPositiveButtonClickListener{            // if user click on ok button
                   Log.d("DATEPICKER",datepicker.headerText)

                  val dateFormatter = SimpleDateFormat("dd-mm-yyyy ")
                   val date = dateFormatter.format(Date(it))

                   val intent = Intent(this, QuestionActivity::class.java)

                   intent.putExtra("DATE",date)
                    startActivity(intent)

                }


              datepicker.addOnNegativeButtonClickListener{             // if user click on cancle button
               Log.d("DATEPICKER",datepicker.headerText)
                }

             datepicker.addOnCancelListener{

                Log.d("DATEPICKER","DATE Picker canclled")     // if user click on back button
                       }



         }
        }




              fun setupfirestore(){

             firestore = FirebaseFirestore.getInstance()

            val collectionRefrance = firestore.collection("quizes")

             collectionRefrance.addSnapshotListener { value, error ->

                  if( value==null ||  error!=null){

                      Toast.makeText(this,"error fetching data",Toast.LENGTH_SHORT).show()
                      return@addSnapshotListener
                  }

                Log.d("DATA",value.toObjects(Quiz::class.java).toString())

                 quizlist.clear()
                 quizlist.addAll(value.toObjects(Quiz::class.java))
                 adapter.notifyDataSetChanged()

             }
        }


              fun setuprecyclerview(){

           adapter = QuizAdapter( this,quizlist)
           QuizRecyclerView.layoutManager = GridLayoutManager(this,2)
            QuizRecyclerView.adapter = adapter

               }



           fun setupdrawerlayout(){
               setSupportActionBar(AppBar)
              actionBarDrawerToggle = ActionBarDrawerToggle(this,mainDrawer,R.string.app_name,R.string.app_name)
              actionBarDrawerToggle.syncState()

            NavigationView.setNavigationItemSelectedListener{

                val intent = Intent(this,ProfileActivity::class.java)
                       startActivity(intent)
                mainDrawer.closeDrawers()
                     true
                   }

                }

           override fun onOptionsItemSelected(item: MenuItem): Boolean {
               if(actionBarDrawerToggle.onOptionsItemSelected(item))
                    return true
                  return super.onOptionsItemSelected(item)
                 }



       }
