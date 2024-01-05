package com.example.eathealthy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eathealthy.R
import com.example.eathealthy.entities.CategoryItems
import com.example.eathealthy.entities.Recipes


class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.RecipeViewHolder>() {

    var listener: OnItemClickListener? = null
    var ctx: Context?= null
    var arrMainCategory = ArrayList<CategoryItems>()
    class RecipeViewHolder(view: View):RecyclerView.ViewHolder(view){
        val tvDishName: TextView = view.findViewById(R.id.tv_dish_name)

    }

    //set-> create setData function to set data from home activity
    fun setData(arrData: List<CategoryItems>) {
        arrMainCategory = arrData as ArrayList<CategoryItems>
    }

    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        return RecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_main_category,parent,false))
    }

    override fun getItemCount(): Int {
        return arrMainCategory.size

    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.tvDishName.text = arrMainCategory[position].strcategory
        Glide.with(ctx!!).load(arrMainCategory[position].strcategorythumb).into(holder.itemView.findViewById(R.id.img_dish))
        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(arrMainCategory[position].strcategory)
        }
    }

    interface OnItemClickListener{
        fun onClicked(categoryName:String)
    }
}