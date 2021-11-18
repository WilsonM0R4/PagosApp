package com.example.pagosapp.models

import java.io.Serializable

data class StatusModel(var status:String, var reason:String, var message:String, var date:String)
    : Serializable
