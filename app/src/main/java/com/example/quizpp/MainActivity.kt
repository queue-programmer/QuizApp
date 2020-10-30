package com.example.quizpp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var currentQuestionCounter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        question_button.setOnClickListener {
            val apiService = APIservice()
            apiService.getQuestion(this, object : QuestionInterface{

                override fun onQustionRecieved(question: Question) {
                    question_Box.text = question.question
                    currentQuestionCounter++
                }

                override fun allQuestionsComplete() {
                    question_Box.text = "You're done! ^^"
                    currentQuestionCounter = 0
                }
            }, currentQuestionCounter)
        }
    }
}