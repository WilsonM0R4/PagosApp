package com.example.pagosapp.models

data class AdditionalModel(var merchantCode:String, var terminalNumber:String,
                           var credit: CreditModel, var totalAmount:Double, var interestAmount:Double,
                           var installmentAmount:Double, var iceAmount: Double, var batch:String,
                           var line:String, var bin:String, var expiration:String)
