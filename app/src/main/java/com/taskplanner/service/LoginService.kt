package com.taskplanner.service

import com.taskplanner.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("auth")
    fun getToken(@Body user: User): Call<Token>
}