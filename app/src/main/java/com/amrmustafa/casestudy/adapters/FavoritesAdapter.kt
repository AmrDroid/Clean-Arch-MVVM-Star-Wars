package com.amrmustafa.casestudy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrmustafa.casestudy.databinding.ItemFavoriteBinding
import com.amrmustafa.casestudy.models.FavoritePresentation

internal class FavoritesAdapter(private val onClick: (FavoritePresentation) -> Unit) :
    ListAdapter<FavoritePresentation, FavoritesAdapter.ViewHolder>(FavoritesDiff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onClick = onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    internal class ViewHolder(
        private val binding: ItemFavoriteBinding,
        private val onClick: (FavoritePresentation) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favorite: FavoritePresentation) {
            binding.favorite = favorite
            binding.executePendingBindings()
            binding.favCard.setOnClickListener {
                onClick(favorite)
            }
        }
    }

    companion object FavoritesDiff : DiffUtil.ItemCallback<FavoritePresentation>() {
        override fun areItemsTheSame(
            old: FavoritePresentation,
            new: FavoritePresentation
        ): Boolean =
                    old.characterPresentation.name == new.characterPresentation.name &&
                    old.characterPresentation.birthYear == new.characterPresentation.birthYear &&
                    old.characterPresentation.heightInCm == new.characterPresentation.heightInCm

        override fun areContentsTheSame(
            oldItem: FavoritePresentation,
            newItem: FavoritePresentation
        ): Boolean = oldItem == newItem
    }
}