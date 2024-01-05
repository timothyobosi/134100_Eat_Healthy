package com.example.eathealthy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eathealthy.adapter.MainCategoryAdapter
import com.example.eathealthy.adapter.SubCategoryAdapter
import com.example.eathealthy.database.RecipeDatabase
import com.example.eathealthy.entities.Category
import com.example.eathealthy.entities.CategoryItems
import com.example.eathealthy.entities.Recipes
import kotlinx.coroutines.launch

class HomeActivity : BaseActivity() {

    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<Recipes>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getDataFromDb()

        arrSubCategory.add(Recipes(1,"Beef and mustard pie"))
        arrSubCategory.add(Recipes(2,"Chicken and mushroom  hotpot"))
        arrSubCategory.add(Recipes(3,"Banana pancakes"))
        arrSubCategory.add(Recipes(4,"kapsalon"))
        arrSubCategory.add(Recipes(5,"Capati and Stew"))
        arrSubCategory.add(Recipes(6,"Rice and matoke"))
        arrSubCategory.add(Recipes(7,"Barbecue and minced meat"))
        arrSubCategory.add(Recipes(8,"Omellete and eggs"))
        arrSubCategory.add(Recipes(9,"Pilau and Sharwama"))
        arrSubCategory.add(Recipes(10,"Fried Potatoes"))

        subCategoryAdapter.setData(arrSubCategory)


        val rv_sub_category :RecyclerView = findViewById(R.id.rv_sub_category)
        rv_sub_category.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_sub_category.adapter = subCategoryAdapter

    }

    private fun getDataFromDb(){
        launch {
            this.let {
                var cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()
                mainCategoryAdapter.setData(arrMainCategory)

                val rv_main_category :RecyclerView = findViewById(R.id.rv_main_category)
                rv_main_category.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                rv_main_category.adapter =mainCategoryAdapter

            }



        }
    }

}