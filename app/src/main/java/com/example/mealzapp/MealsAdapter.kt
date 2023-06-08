package com.example.mealzapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.domain.entity.Category
import com.example.mealzapp.databinding.CategoryItemBinding

class MealsAdapter:RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {
    private val callback = object : DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val binding =CategoryItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return MealsViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        val category=differ.currentList[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int =differ.currentList.size




    inner class MealsViewHolder(
        val binding: CategoryItemBinding
    ) :RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category){
            binding.categoryNameTv.text = category.strCategory.toString()
            binding.categoryDesTv.text = category.strCategoryDescription.toString()
            Glide.with(binding.categoryIv.context).load(category.strCategoryThumb)
                .placeholder(R.drawable.ic_loading).error(R.drawable.ic_no_image)
                .transform(CenterCrop(), RoundedCorners(15))
                .into(binding.categoryIv)

        }

    }
}