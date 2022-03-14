package com.canliskor.utils


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


sealed class CSResource<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : CSResource<T>(data)

    class Error<T>(message: String, data: T? = null) : CSResource<T>(data, message)

    class Loading<T> : CSResource<T>()
}