package com.example.pagosapp.common

import android.app.Application
import android.content.Context
import android.os.Build
import java.math.BigInteger
import java.net.InetAddress
import java.security.MessageDigest
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.util.*
import android.net.wifi.WifiManager
import android.text.format.Formatter.formatIpAddress
import androidx.core.content.ContentProviderCompat

import androidx.core.content.ContentProviderCompat.requireContext




class Commons {

    companion object {
        fun getRandom(): String {
            return BigInteger(128, SecureRandom()).toString()
        }

        fun getCurrentDateFormat(): String {
            return SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mmZ",
                Locale.getDefault()
            ).format(Date())
        }

        fun getBase64(input: ByteArray): String {
            val encodedBytes: ByteArray = if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.O
            ) {
                Base64.getEncoder().encode(input)
            } else {
                android.util.Base64.encode(input, android.util.Base64.NO_WRAP)
            }
            return String(encodedBytes)
        }

        fun getSHA256(input: String): ByteArray {
            val mDigest: MessageDigest = MessageDigest.getInstance("SHA-256")
            return mDigest.digest(input.toByteArray())
        }

        fun getIPAddress(): String {
            return InetAddress.getLocalHost().hostAddress!!
        }

        fun getUserAgent(): String {
            return "Android"
        }
    }


}