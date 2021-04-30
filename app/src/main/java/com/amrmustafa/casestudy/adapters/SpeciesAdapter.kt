package com.amrmustafa.casestudy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrmustafa.casestudy.databinding.ItemSpecieBinding
import com.amrmustafa.casestudy.models.SpeciePresentation

internal class SpeciesAdapter :
    ListAdapter<SpeciePresentation, SpeciesAdapter.ViewHolder>(
        CharacterSpecieDiffer
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSpecieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    internal class ViewHolder(private val binding: ItemSpecieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(species: SpeciePresentation) {
            binding.species = species
            binding.executePendingBindings()
        }
    }

    companion object CharacterSpecieDiffer : DiffUtil.ItemCallback<SpeciePresentation>() {
        override fun areItemsTheSame(
            oldItem: SpeciePresentation,
            newItem: SpeciePresentation
        ): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: SpeciePresentation,
            newItem: SpeciePresentation
        ): Boolean = oldItem == newItem
    }
}