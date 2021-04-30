package com.amrmustafa.casestudy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrmustafa.casestudy.databinding.ItemSearchBinding
import com.amrmustafa.casestudy.models.CharacterPresentation

internal class SearchResultAdapter(private val onClick: (CharacterPresentation) -> Unit) :
    ListAdapter<CharacterPresentation, SearchResultAdapter.ViewHolder>(SearchResultDiff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSearchBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onClick = onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

     class ViewHolder(
        private val binding: ItemSearchBinding,
        private val onClick: (CharacterPresentation) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: CharacterPresentation) {
            binding.searchedCharacter = character
            binding.executePendingBindings()
            binding.constraintlayout.setOnClickListener {
                onClick(character)
            }
            binding.moreInfoArrowImageButton.setOnClickListener {
                onClick(character)
            }
        }
    }

    companion object SearchResultDiff : DiffUtil.ItemCallback<CharacterPresentation>() {
        override fun areItemsTheSame(
            oldItem: CharacterPresentation,
            newItem: CharacterPresentation
        ): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(
            oldItem: CharacterPresentation,
            newItem: CharacterPresentation
        ): Boolean = oldItem == newItem
    }
}