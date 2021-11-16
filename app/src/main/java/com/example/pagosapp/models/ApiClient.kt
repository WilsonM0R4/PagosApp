package com.example.pagosapp.models

import android.util.Log
import com.example.pagosapp.interfaces.GatewayProcess
import com.example.pagosapp.interfaces.PaymentResponseCallback
import com.example.pagosapp.interfaces.QueryResponseCallback
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    lateinit var paymentCallback : PaymentResponseCallback
    lateinit var queryCallback: QueryResponseCallback

    private fun getClient ():Retrofit {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Content-Type", "application/json")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()
        return Retrofit.Builder().baseUrl("https://dev.placetopay.com/rest/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    fun sendPayment(request:PayRequestModel) {

            val response = getClient().create(GatewayProcess::class.java).sendProcess(request).enqueue(object:Callback<ResponseModel> {

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Log.e("Call Error", "Ha fallado el servicio")
                    val status = StatusModel("FAILURE", "00", "Ha ocurrido un error al procesar la transaccion","")
                    paymentCallback.taskDidFail(status)

                }

                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    Log.e("Call result", "hay resultado")
                    if (response.isSuccessful) {
                        response.body()?.let { paymentCallback.taskDidFinish(it) }
                    } else {
                        val status = StatusModel("FAILURE", "00",
                            "Ha ocurrido un error al procesar la transaccion: "+response.code(),
                            "")

                        paymentCallback.taskDidFail(status)
                    }

                }

            })
    }

    fun getTransactions(queryModel: QueryModel) {
        val response = getClient().create(GatewayProcess::class.java).getTransactions(queryModel).enqueue(object:Callback<QueryResponseModel> {

            override fun onFailure(call: Call<QueryResponseModel>, t: Throwable) {
                Log.e("Call Error", "Ha fallado el servicio")
                val status = StatusModel("FAILURE", "00", "Ha ocurrido un error al procesar la transaccion","")
                queryCallback.taskDidFail(status)

            }

            override fun onResponse(
                call: Call<QueryResponseModel>,
                response: Response<QueryResponseModel>
            ) {
                Log.e("Call result", "hay resultado")
                if (response.isSuccessful) {
                    response.body()?.let { queryCallback.taskDidFinish(it) }
                } else {
                    val status = StatusModel("FAILURE", "00",
                        "Ha ocurrido un error al procesar la transaccion: "+response.code(),
                        "")

                    paymentCallback.taskDidFail(status)
                }

            }

        })
    }
}