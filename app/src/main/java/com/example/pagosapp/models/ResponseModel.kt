package com.example.pagosapp.models

import java.io.Serializable

data class ResponseModel(var status:StatusModel, var date:String, var transactionDate:String,
                         var internalReference:Int, var reference:String, var paymentMethod:String,
                         var franchise:String, var franchiseName:String, var issuerName:String,
                         var amount: AmountModel, var conversion:ConversionModel,
                         var authorization:String, var receipt:String, var type:String,
                         var refunded:Boolean, var lastDigits:String, var provider:String,
                         var discount:DiscountModel, var processorFields:ProcessorFieldsModel
                         ) : Serializable//var additional:AdditionalModel
