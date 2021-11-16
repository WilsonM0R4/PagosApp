package com.example.pagosapp.common

import com.example.pagosapp.models.InstrumentModel
import com.example.pagosapp.models.PayerModel
import com.example.pagosapp.models.PaymentModel

class SharedData {

    companion object {
        lateinit var payerModel : PayerModel
        lateinit var paymentModel: PaymentModel
        lateinit var instrumentModel: InstrumentModel
    }

}