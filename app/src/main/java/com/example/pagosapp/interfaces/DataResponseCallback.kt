package com.example.pagosapp.interfaces

import com.example.pagosapp.models.ResponseModel
import com.example.pagosapp.models.StatusModel

interface PaymentResponseCallback {

    fun taskDidFinish(response:ResponseModel)
    fun taskDidFail(status:StatusModel)

}