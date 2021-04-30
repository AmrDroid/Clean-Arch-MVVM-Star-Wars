package com.amrmustafa.casestudy.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrmustafa.casestudy.databinding.ItemFilmBinding
import com.amrmustafa.casestudy.models.FilmPresentation

internal class FilmsAdapter : ListAdapter<FilmPresentation, FilmsAdapter.ViewHolder>(FilmDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    internal class ViewHolder(private val binding: ItemFilmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(film: FilmPresentation) {
            binding.film = film
            binding.executePendingBindings()
        }
    }

    companion object FilmDiffer : DiffUtil.ItemCallback<FilmPresentation>() {
        override fun areItemsTheSame(
            oldItem: FilmPresentation,
            newItem: FilmPresentation
        ): Boolean =
            oldItem.title == newItem.title

        override fun areContentsTheSame(
            oldItem: FilmPresentation,
            newItem: FilmPresentation
        ): Boolean = oldItem == newItem
    }
}