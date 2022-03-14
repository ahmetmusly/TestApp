package com.canliskor.utils

import android.annotation.SuppressLint
import android.util.Base64
import com.canliskor.utils.CSConstants.Companion.AES_IV
import com.canliskor.utils.CSConstants.Companion.AES_KEY
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


object CSGenerateToken {
    private fun encrypt(date: String): String? = try {

        val tokenString = "{canliskor.android}-$date"

        val sKeySpec = SecretKeySpec(AES_KEY.toByteArray(), "AES")

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, IvParameterSpec(AES_IV.toByteArray()))

        val encrypted = cipher.doFinal(tokenString.toByteArray())

        Base64.encodeToString(encrypted, Base64.URL_SAFE)

    } catch (h: Exception) {
        ""
    }

    @SuppressLint("SimpleDateFormat")
    fun getAESToken(): String? = encrypt(
        SimpleDateFormat("yyyyMMddHHmmss")
            .format(Calendar.getInstance(TimeZone.getTimeZone("GMT+03")).time)
    )
}