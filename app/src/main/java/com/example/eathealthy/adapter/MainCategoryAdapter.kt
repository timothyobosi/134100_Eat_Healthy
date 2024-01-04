package com.example.eathealthy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eathealthy.R
import com.example.eathealthy.entities.Recipes

class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.RecipeViewHolder>() {

    var arrMainCategory = ArrayList<Recipes>()
    class RecipeViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvDishName: TextView = view.findViewById(R.id.tv_dish_name)
    }

    //set-> create setData function to set data from home activity
    fun setData(arrData: List<Recipes>) {
        arrMainCategory = arrData as ArrayList<Recipes>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main_category,parent,false))
    }

    override fun getItemCount(): Int {
        return arrMainCategory.size

    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.tvDishName.text = arrMainCategory[position].dishName
        // Replace "dishName" with the actual property name in your Recipes class
    }
}