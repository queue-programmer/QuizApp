package com.example.quizpp

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon


class APIservice {

    val questions = listOf(
        "https://run.mocky.io/v3/620e1347-b3ea-43ad-98e3-41023891bf85",
        "https://run.mocky.io/v3/c2068670-6d09-4fe8-b9ff-e33ab1cde433",
        "https://run.mocky.io/v3/7970d30c-9bc8-4c6d-80a1-6b364ed8a34d",
        "https://run.mocky.io/v3/c700a1b2-eccd-4b81-b60a-6f4076087e5d",
        "https://run.mocky.io/v3/e86b261e-41ee-4bf4-9856-1de688ec18d8"
    )


    fun getQuestion(Context: Context, questionInterface: QuestionInterface, questionIndex: Int) {

        //Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(Context)

        var index = questionIndex
        if(index >= questions.size){
            questionInterface.allQuestionsComplete()
            }else{
                val url = questions[questionIndex]


        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->

                val result = Klaxon().parse<Question>(response)

                result?.let {
                    Log.d("LOG_RESULT", it.question)
                    questionInterface.onQustionRecieved(it)
                }
            },
            Response.ErrorListener {
                Log.d("LOG_RESULT", "That didn't work!")
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        }
    }
}