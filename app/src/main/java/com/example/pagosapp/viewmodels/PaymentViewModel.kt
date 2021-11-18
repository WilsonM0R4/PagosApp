package com.example.pagosapp.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pagosapp.common.Commons
import com.example.pagosapp.common.Encoding
import com.example.pagosapp.interfaces.PaymentResponseCallback
import com.example.pagosapp.models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaymentViewModel : ViewModel(), PaymentResponseCallback {

    var paymentResponse: MutableLiveData<ResponseModel> = MutableLiveData<ResponseModel>()

    fun sendPayment(payerModel: PayerModel, paymentModel: PaymentModel,
                    instrumentModel: InstrumentModel) {
        val apiClient = ApiClient()
        apiClient.paymentCallback = this
        CoroutineScope(Dispatchers.IO).launch {
            val request = PayRequestModel(
                Encoding().generateCredentials(), payerModel, paymentModel,
                instrumentModel, Commons.getIPAddress(), Commons.getUserAgent()
            )

            Log.e("ViewModel", "before call")
            apiClient.sendPayment(request)
        }
    }

    override fun taskDidFinish(response: ResponseModel) {
        paymentResponse.value = response
    }

    override fun taskDidFail(status: StatusModel) {
        paymentResponse.value = ResponseModel(status,"", "", 0,
            "", "", "", "", "",
            AmountModel("",0),
            ConversionModel(AmountModel("",0), AmountModel("",0), 0),
            "", "", "", false, "","",DiscountModel("","",0,0, ""),
            ProcessorFieldsModel("", ""))
            //AdditionalModel("","",CreditModel("","",
               // "",0),0.0,0.0, 0.0,
                //0.0, "", "", "", ""))
    }

}