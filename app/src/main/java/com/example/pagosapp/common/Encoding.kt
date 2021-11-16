package com.example.pagosapp.common

import com.example.pagosapp.common.Commons
import com.example.pagosapp.models.AuthModel

class Encoding {

    private val tranKey = "024h1IlD"
    private val loginKey = "6dd490faf9cb87a9862245da41170ff2"
    private val nonce = Commons.getRandom()
    private val seed = Commons.getCurrentDateFormat()
    private val nonceBase64 = Commons.getBase64(nonce.toByteArray())
    private val tranKeyBase64 = Commons.getBase64(Commons.getSHA256(nonce + seed + tranKey))

    fun generateCredentials() : AuthModel {
        return AuthModel(loginKey, tranKeyBase64, nonceBase64, seed)
    }
}