package com.example.pagosapp.models

data class PayRequestModel(var auth:AuthModel, var payer:PayerModel, var payment:PaymentModel,
                           var instrument: InstrumentModel, var ipAddress:String,
                           var userAgent:String)
