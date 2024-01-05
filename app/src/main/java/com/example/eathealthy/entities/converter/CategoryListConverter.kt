package com.example.eathealthy.entities.converter

import androidx.room.TypeConverter
import com.example.eathealthy.entities.CategoryItems
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryListConverter {

    @TypeConverter
    fun fromCategoryList(categoryList: List<CategoryItems>?): String? {
        return Gson().toJson(categoryList)
    }

    @TypeConverter
    fun toCategoryList(categoryListString: String?): List<CategoryItems>? {
        val type = object : TypeToken<List<CategoryItems>>() {}.type
        return Gson().fromJson(categoryListString, type)
    }
}
