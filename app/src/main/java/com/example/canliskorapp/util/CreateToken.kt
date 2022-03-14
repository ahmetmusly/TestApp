package com.example.canliskorapp.util

import android.annotation.SuppressLint
import android.util.Base64
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object CreateToken {

    val AES_KEY = "WTR82D4APQHAJ6W6HTLM2BW1I7552A6W"
    val AES_IV = "CA9RWBYYD133K6Z3"

    private fun Encrypt(tokenString: String): String? {
        return try {
            val skeySpec = SecretKeySpec(AES_KEY.toByteArray(), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, IvParameterSpec(AES_IV.toByteArray()))
            val encrypted = cipher.doFinal(tokenString.toByteArray())
            Base64.encodeToString(encrypted, Base64.URL_SAFE)
        } catch (h: Exception) {
            ""
        }
    }

    fun GetAESToken(): String? {
        @SuppressLint("SimpleDateFormat") val now: String =
            SimpleDateFormat("yyyyMMddHHmmss").format(
                Calendar.getInstance(TimeZone.getTimeZone("GMT+03")).getTime()
            )
        return Encrypt("{sportmen.android}-$now")
    }
}