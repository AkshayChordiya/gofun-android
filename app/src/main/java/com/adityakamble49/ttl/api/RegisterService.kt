package com.adityakamble49.ttl.api

import com.adityakamble49.ttl.model.Token
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author Akshay Chordiya
 * @since 14/3/2017.
 */
interface RegisterService {

    @POST("register/")
    @FormUrlEncoded
    fun register(@Field("email") emailAddress: String, @Field("username") username: String,
                 @Field("password") password: String,  @Field("device_token") deviceId: String): Call<Token>
}