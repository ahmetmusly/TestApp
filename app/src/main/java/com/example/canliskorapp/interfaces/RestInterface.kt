/*
package com.example.canliskorapp.interfaces


import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url


interface RestInterface {
    @GET("/registerDeviceAPNS")
    fun setRegisterToken(
        @Query("appRef") appRef: String?,
        @Query("bundleId") bundleId: String?,
        @Query("did") did: String?,
        @Query("tokenId") tokenId: String?,
        @Query("dev") dev: Boolean
    ): Observable<String?>?

    @GET("/pushOpened")
    fun setOpenedPush(
        @Query("appRef") appRef: String?,
        @Query("bundleId") bundleId: String?,
        @Query("did") did: String?,
        @Query("tokenId") tokenId: String?,
        @Query("pid") pid: Int,
        @Query("dev") dev: Boolean
    ): Observable<String?>?

    @GET
    fun getAndroidConfig(@Url path: String?): Observable<ConfigData?>?

    @GET
    fun getCatAndSource(@Url path: String?): Observable<SourceResponse?>?

    @GET
    fun  // url configden alındığından ve ? işaretini %3F ye çevirdiği için @Url tipinde ve sadece GET ile kullanıldı
            getToday(
        @Url path: String?,
        @QueryMap paramsMap: Map<String?, String?>?
    ): Observable<NewsResponse?>?

    @GET
    fun getLastMinute(@Url path: String?): Observable<NewsResponse?>?

    @GET
    fun getTrend(@Url path: String?): Observable<TrendResponse?>?

    @GET
    fun getSearch(@Url path: String?, @Query("term") term: String?): Observable<NewsResponse?>?

    @GET
    fun getDiscover(@Url path: String?): Observable<DiscoverResponse?>?

    @GET
    fun getNewsDetail(
        @Url path: String?,
        @Query("id") id: String?
    ): Observable<NewsDetailResponse?>?

    @GET
    fun getNotifications(@Url path: String?): Observable<NewsResponse?>?
}*/
