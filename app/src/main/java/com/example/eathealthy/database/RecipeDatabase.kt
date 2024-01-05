package com.example.eathealthy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eathealthy.dao.RecipeDao
import com.example.eathealthy.entities.Category
import com.example.eathealthy.entities.CategoryItems
import com.example.eathealthy.entities.Recipes
import com.example.eathealthy.entities.converter.CategoryListConverter

@Database(entities = [Recipes::class,CategoryItems::class,Category::class],version = 1,exportSchema = false)
@TypeConverters(CategoryListConverter::class)
abstract class RecipeDatabase: RoomDatabase() {

    companion object{

        var recipesDatabase:RecipeDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): RecipeDatabase{
            if (recipesDatabase == null){
                recipesDatabase = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                ).build()
            }
            return recipesDatabase!!
        }
    }

    abstract fun recipeDao(): RecipeDao
}