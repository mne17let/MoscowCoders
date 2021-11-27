package com.sportrgu.data.cloud

import android.util.Log
import com.sportrgu.data.models.people.Booking
import com.sportrgu.interfaces.CloudSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CloudSourceImplementation : CloudSource {

    private val TAG_CLOUD_SOURCE = "MyCloudSource"

    val retrofit = Retrofit
        .Builder()
        .baseUrl("https://moscowcoders-default-rtdb.firebaseio.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(FirebaseApi::class.java)

    override fun sendBooking(newBooking: Booking) {

        val timeStart = newBooking.timeStartString
        val userId = newBooking.userId
        if (timeStart != null && userId != null) {
            val call = api.sendNewBooking(
                newBooking.userId,
                newBooking.timeStartString,
                newBooking
            )

            call.enqueue(object : Callback<Booking> {
                override fun onResponse(
                    call: Call<Booking>,
                    response: Response<Booking>
                ) {
                    Log.d(TAG_CLOUD_SOURCE, "Хедер ответа: ${response.headers()}")
                    Log.d(TAG_CLOUD_SOURCE, "Код ответа: ${response.code()}")
                    Log.d(TAG_CLOUD_SOURCE, "Тело ответа: ${response.body()}")
                }

                override fun onFailure(call: Call<Booking>, t: Throwable) {
                    Log.d(TAG_CLOUD_SOURCE, "Ошибка запроса: ${t}")
                }

            })
        }
    }

    override fun getBooking() {

    }
}