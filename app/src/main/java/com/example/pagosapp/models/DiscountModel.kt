package com.example.pagosapp.models

import java.io.Serializable

data class DiscountModel(var code:String, var type:String, var amount:Int,
                         var base:Int, var percent:String) : Serializable
