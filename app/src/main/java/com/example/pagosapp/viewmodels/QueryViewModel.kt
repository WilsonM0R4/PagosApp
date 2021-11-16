package com.example.pagosapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pagosapp.common.Encoding
import com.example.pagosapp.interfaces.QueryResponseCallback
import com.example.pagosapp.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Response

class QueryViewModel:ViewModel(), QueryResponseCallback {

    var queryResponse: MutableLiveData<QueryResponseModel> = MutableLiveData<QueryResponseModel>()

    fun getTransactions(reference:String, currency:String, total:Int) {

        val client = ApiClient()
        client.queryCallback = this
        CoroutineScope(Dispatchers.IO).launch {
            val amount = AmountModel(currency, total)
            val queryRequest = QueryModel(Encoding().generateCredentials(), reference, amount)

            client.getTransactions(queryRequest)
        }
    }

    override fun taskDidFinish(response: QueryResponseModel) {
        queryResponse.value = response
    }

    override fun taskDidFail(status: StatusModel) {
        val empty = ArrayList<ResponseModel>()
        queryResponse.value = QueryResponseModel(status, empty)
    }

}