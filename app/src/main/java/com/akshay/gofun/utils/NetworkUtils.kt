package com.akshay.gofun.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
class NetworkUtils {

    /* Static Declaration in Kotlin */
    companion object {
        val BASE_URL = "https://ttl-server.herokuapp.com/api/"
        val retrofit = Retrofit.Builder()
                .baseUrl(NetworkUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()!!
    }

}