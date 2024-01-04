package com.example.eathealthy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eathealthy.adapter.MainCategoryAdapter
import com.example.eathealthy.adapter.SubCategoryAdapter
import com.example.eathealthy.entities.Recipes

class HomeActivity : AppCompatActivity() {

    var arrMainCategory = ArrayList<Recipes>()
    var arrSubCategory = ArrayList<Recipes>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        //temporary data
        arrMainCategory.add(Recipes(1,"Beef"))
        arrMainCategory.add(Recipes(2,"Chicken"))
        arrMainCategory.add(Recipes(3,"Dessert"))
        arrMainCategory.add(Recipes(4,"Lamb"))

        mainCategoryAdapter.setData(arrMainCategory)

        arrSubCategory.add(Recipes(1,"Beef and mustard pie"))
        arrSubCategory.add(Recipes(2,"Chicken and mushroom  hotpot"))
        arrSubCategory.add(Recipes(3,"Banana pancakes"))
        arrSubCategory.add(Recipes(4,"kapsalon"))

        subCategoryAdapter.setData(arrSubCategory)



        val rv_main_category :RecyclerView = findViewById(R.id.rv_main_category)
        rv_main_category.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_main_category.adapter =mainCategoryAdapter

        val rv_sub_category :RecyclerView = findViewById(R.id.rv_sub_category)
        rv_sub_category.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_sub_category.adapter = subCategoryAdapter



    }
}