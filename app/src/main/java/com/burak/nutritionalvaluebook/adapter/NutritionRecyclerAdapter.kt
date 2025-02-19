package com.burak.nutritionalvaluebook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.burak.nutritionalvaluebook.databinding.NutritionRecyclerRowBinding
import com.burak.nutritionalvaluebook.model.Nutrition
import com.burak.nutritionalvaluebook.view.NutritionListFragmentDirections

class NutritionRecyclerAdapter(val nutritionList : ArrayList<Nutrition>) : RecyclerView.Adapter<NutritionRecyclerAdapter.NutritionViewHolder>() {

    class NutritionViewHolder(val binding: NutritionRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionViewHolder {
        val binding = NutritionRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NutritionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  nutritionList.size
    }

    fun nutritionUpdateList(newNutritionList: List<Nutrition>) {
        nutritionList.clear()
        nutritionList.addAll(newNutritionList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NutritionViewHolder, position: Int) {
        holder.binding.name.text = nutritionList[position].nutritionName
        holder.binding.calories.text = nutritionList[position].nutritionCalorie

        holder.itemView.setOnClickListener {
            val action = NutritionListFragmentDirections.actionNutritionListFragmentToNutritionDetailFragment(nutritionList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }
}