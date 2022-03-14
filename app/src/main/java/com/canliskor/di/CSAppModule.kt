package com.canliskor.di

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.canliskor.api.ApiHelper
import com.canliskor.api.ApiHelperImpl
import com.canliskor.api.ApiService
import com.canliskor.app.LiveScoreApp
import com.canliskor.utils.CSConstants
import com.example.canliskorapp.R
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Ahmet_MUŞLUOĞLU on 31.12.2021
 */


@Module
@InstallIn(SingletonComponent::class)
class CSAppModule {

    @Singleton
    @Provides
    fun provideBaseUrl() = CSConstants.BASE_URL

//    @ApiKey
//    @Singleton
//    @Provides
//    fun provideApiKey() = "v1/link/531"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideApp(app: LiveScoreApp): LiveScoreApp = app

//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context/*, callback: MyDatabase.Callback*/) =
//        Room.databaseBuilder(context, MomentDatabase::class.java, Constants.DB_NAME)
//            .fallbackToDestructiveMigration()
////            .addCallback(callback)
//            .build()
//
//    @Provides
//    fun provideDao(db: MomentDatabase) = db.getDao()

    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions()
//                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ball_icon)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
        )
}