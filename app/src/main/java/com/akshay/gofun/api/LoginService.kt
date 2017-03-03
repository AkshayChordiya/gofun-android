package com.akshay.gofun.api

import com.akshay.gofun.model.Token
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author Akshay Chordiya
 * @since 3/3/2017.
 */
interface LoginService {

    @POST("login/")
    @FormUrlEncoded
    fun login(@Field("username") emailAddress: String, @Field("password") password: String,
              @Field("device_token") deviceId: String): Call<Token>
}