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

        // Example CategoryItems with ingredients and preparation steps
        val categoryItem = CategoryItems(
            id = 1,
            idcategory = "Beef",
            strcategory = "Beef",
            strcategorythumb = "Thumbnail URL",
            strcategorydescription = "Description",
            ingredients = "Beef, Salt, Pepper",
            preparationSteps = "1. Marinate beef with salt and pepper\n2. Grill until cooked"

        )

        arrMainCategory.add(categoryItem)



        val rv_sub_category :RecyclerView = findViewById(R.id.rv_sub_category)
        rv_sub_category.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_sub_category.adapter = subCategoryAdapter

        // Example CategoryItems with ingredients and preparation steps
        arrMainCategory.add (CategoryItems(
            id = 1,
            idcategory = "Beef",
            strcategory = "Beef",
            strcategorythumb = "Thumbnail URL",
            strcategorydescription = "Description",
            ingredients = "Beef, Salt, Pepper",
            preparationSteps = "1. Marinate beef with salt and pepper\n2. Grill until cooked"

        )
        )

        arrMainCategory.add(
            CategoryItems(
                id = 2,
                idcategory = "Chicken",
                strcategory = "Chicken",
                strcategorythumb = "Thumbnail URL",
                strcategorydescription = "Description",
                ingredients = "Chicken, Mushrooms, Salt, Pepper",
                preparationSteps = "1. Marinate chicken with salt and pepper\n2. Cook with mushrooms"
            )
        )

        arrMainCategory.add(
            CategoryItems(
                id = 3,
                idcategory = "Dessert",
                strcategory = "Banana pancakes",
                strcategorythumb = "Thumbnail URL",
                strcategorydescription = "Description",
                ingredients = "Bananas, Flour, Eggs, Milk",
                preparationSteps = "1. Mash bananas and mix with flour, eggs, and milk\n2. Cook as pancakes"
            )

        )
        // Recipe 4
        arrMainCategory.add(
            CategoryItems(
                id = 4,
                idcategory = "Lamb",
                strcategory = "Lamb",
                strcategorythumb = "Thumbnail URL",
                strcategorydescription = "Description",
                ingredients = "Lamb, Herbs, Garlic, Salt, Pepper",
                preparationSteps = "1. Marinate lamb with herbs, garlic, salt, and pepper\n2. Roast until tender"
            )
        )

// Recipe 5
        arrMainCategory.add(
            CategoryItems(
                id = 5,
                idcategory = "Beef",
                strcategory = "Beef",
                strcategorythumb = "Thumbnail URL",
                strcategorydescription = "Description",
                ingredients = "Beef, Potatoes, Cheese, Sauce",
                preparationSteps = "1. Cook beef and layer with potatoes\n2. Add cheese and sauce, bake until golden"
            )
        )

// Recipe 6
        arrMainCategory.add(
            CategoryItems(
                id = 6,
                idcategory = "Chicken",
                strcategory = "Chicken and Rice",
                strcategorythumb = "Thumbnail URL",
                strcategorydescription = "Description",
                ingredients = "Chicken, Rice, Vegetables, Soy Sauce",
                preparationSteps = "1. Cook chicken and rice with vegetables\n2. Add soy sauce for flavor"
            )
        )

// Continue this process for the remaining recipes...

// Recipe 7
        arrMainCategory.add(
            CategoryItems(
                id = 7,
                idcategory = "Dessert",
                strcategory = "Chocolate Cake",
                strcategorythumb = "Thumbnail URL",
                strcategorydescription = "Description",
                ingredients = "Flour, Sugar, Cocoa Powder, Eggs",
                preparationSteps = "1. Mix ingredients and bake until a toothpick comes out clean"
            )
        )

// Recipe 8
        arrMainCategory.add(
            CategoryItems(
                id = 8,
                idcategory = "Lamb",
                strcategory = "Grilled Lamb Chops",
                strcategorythumb = "Thumbnail URL",
                strcategorydescription = "Description",
                ingredients = "Lamb Chops, Rosemary, Garlic, Olive Oil",
                preparationSteps = "1. Marinate lamb chops with rosemary, garlic, and olive oil\n2. Grill until cooked"
            )
        )

// Recipe 9
        arrMainCategory.add(
            CategoryItems(
                id = 9,
                idcategory = "Chicken",
                strcategory = "Chicken Salad",
                strcategorythumb = "Thumbnail URL",
                strcategorydescription = "Description",
                ingredients = "Chicken, Lettuce, Tomatoes, Cucumbers, Dressing",
                preparationSteps = "1. Cook chicken and toss with vegetables and dressing"
            )
        )

// Recipe 10
        arrMainCategory.add(
            CategoryItems(
                id = 10,
                idcategory = "Dessert",
                strcategory = "Fruit Salad",
                strcategorythumb = "Thumbnail URL",
                strcategorydescription = "Description",
                ingredients = "Assorted Fruits, Honey, Mint",
                preparationSteps = "1. Chop fruits and mix with honey, garnish with mint"
            )
        )

        mainCategoryAdapter.setData(arrMainCategory)

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