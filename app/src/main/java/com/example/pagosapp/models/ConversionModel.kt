package com.example.pagosapp.models

import java.io.Serializable

data class ConversionModel(var from:AmountModel, var to:AmountModel, var factor:Int) : Serializable
