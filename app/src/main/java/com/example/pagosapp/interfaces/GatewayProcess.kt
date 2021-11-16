package com.example.pagosapp.interfaces

import com.example.pagosapp.models.PayRequestModel
import com.example.pagosapp.models.QueryModel
import com.example.pagosapp.models.QueryResponseModel
import com.example.pagosapp.models.ResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface GatewayProcess {

    //@Headers("Content-Type:application/json")
    @POST("gateway/process")
    fun sendProcess (@Body request: PayRequestModel) : Call<ResponseModel>

    //@Headers("{Content-Type:application/json, Accept:application/json}")
    @POST("gateway/search")
    fun getTransactions(@Body query : QueryModel) : Call<QueryResponseModel>
}