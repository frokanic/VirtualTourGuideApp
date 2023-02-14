package com.example.museumapp.presentation.all_tours_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.museumapp.databinding.ItemTourBinding
import com.example.museumapp.domain.model.Tour
import com.squareup.picasso.Picasso

class AllToursRecyclerViewAdapter(private val tours: Tour): RecyclerView.Adapter<AllToursRecyclerViewAdapter.AllToursViewHolder>() {

    inner class AllToursViewHolder(val binding: ItemTourBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllToursViewHolder {
        return AllToursViewHolder(
            ItemTourBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: AllToursViewHolder, position: Int) {
        val binding = holder.binding
        val curItem = tours

        holder.itemView.apply {
            Picasso.get().load(curItem.thumbnail).into(binding.ivItemTour)
            binding.tvTitleItemTour.text = curItem.title
            binding.tvAudiolanguagesItemTour.text = "3 languages"
            binding.tvDurationItemTour.text = "${curItem.duration} minutes"
            binding.tvStarsItemTour.text = "${curItem.average_rating}/5 (${curItem.rating_count})"
        }
    }


}