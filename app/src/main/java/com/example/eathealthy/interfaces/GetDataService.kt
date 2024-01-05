package com.example.eathealthy.interfaces

import com.example.eathealthy.entities.Category
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {
    @GET("categories.php")
    fun getCategoryList(): Call<Category>
}