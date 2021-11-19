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
import com.example.pagosapp.models.CardModel


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

        fun getTestCards() : ArrayList<CardModel> {
            val card1 = CardModel("4111111111111111", "11/24", "369", 1)
            val card2 = CardModel("400558 00 00 00 0040", "12/24", "148", 1)
            val card3 = CardModel("542400 00 00 00 0015", "10/24", "257", 1)
            val card4 = CardModel("466666 66 66 66 6669", "08/24", "453", 1)

            val array = ArrayList<CardModel>()
            array.add(card1)
            array.add(card2)
            array.add(card3)
            array.add(card4)

            return array
        }
    }


}