package com.example.mealzapp.di

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.domain.entity.Category
import com.example.mealzapp.R
import com.example.mealzapp.databinding.CategoryItemBinding

class CategoriesAdapter:RecyclerView.Adapter<CategoriesAdapter.ViewHolder>(){

    private val callback = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.strCategoryThumb == newItem.strCategoryThumb
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat=differ.currentList[position]
        holder.bind(cat)
    }

    override fun getItemCount(): Int =differ.currentList.size


    inner class ViewHolder(
        private val binding: CategoryItemBinding
    ) :RecyclerView.ViewHolder(binding.root){
        fun bind(category:Category){
            binding.categoryNameTv.text=category.strCategory
            binding.categoryDesTv.text=category.strCategoryDescription
            Glide.with(binding.categoryIv).load(category.strCategoryThumb)
                .placeholder(R.drawable.ic_loading).error(R.drawable.ic_no_image)
                .transform(CenterCrop(), RoundedCorners(15))
                .into(binding.categoryIv)

        }

    }
}