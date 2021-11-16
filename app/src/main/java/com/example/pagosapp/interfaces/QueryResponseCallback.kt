package com.example.pagosapp.interfaces

import com.example.pagosapp.models.QueryResponseModel
import com.example.pagosapp.models.ResponseModel
import com.example.pagosapp.models.StatusModel

interface QueryResponseCallback {

    fun taskDidFinish(response: QueryResponseModel)
    fun taskDidFail(status: StatusModel)

}