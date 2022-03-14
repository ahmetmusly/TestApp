package com.example.canliskorapp.interfaces

import android.content.Context
import com.example.canliskorapp.util.Preferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*


class ApiClient {
    private var retrofit: Retrofit? = null
    private var ctx: Context? = null
    fun getClientApi(context: Context?): Retrofit? {
        ctx = context
        val GSON = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setLenient()
            .create()
        retrofit = Retrofit.Builder()
            .baseUrl(Preferences.getString(context, "connectedurl", "https://api.tmgrup.com.tr"))
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getUnsafeOkHttpClient()?.build())
            .build()
        return retrofit
    }

    fun getClientAPNS(): Retrofit? {
        val GSON = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setLenient()
            .create()
        retrofit = Retrofit.Builder().baseUrl("https://apns.tmgrup.com.tr/")
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getUnsafeOkHttpClient()?.build())
            .build()
        return retrofit
    }


    fun getUnsafeOkHttpClient(): OkHttpClient.Builder? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return try {
            val trustAllCerts = arrayOf<TrustManager>(
                object : X509TrustManager {
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }
            )
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            val sslSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder().addInterceptor(interceptor)
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname: String?, session: SSLSession? -> true }
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}