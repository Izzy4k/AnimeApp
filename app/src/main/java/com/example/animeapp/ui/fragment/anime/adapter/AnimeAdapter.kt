package com.example.animeapp.ui.fragment.anime.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animeapp.databinding.ItemAnimeBinding
import com.example.domain.anime.entity.Anime

class AnimeAdapter(private val result: Result) :
    PagingDataAdapter<Anime, AnimeAdapter.AnimeViewHolder>(AnimeCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder =
        AnimeViewHolder(
            ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.onBind(position)
        holder.initBtn(position)
    }

    inner class AnimeViewHolder(private val binding: ItemAnimeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val item = getItem(position) ?: return
            with(binding){
                itemImage.load(item.image)
                itemAnimeTitle.text = item.title
                itemAnimeGenre.text = item.genre
            }
        }

        fun initBtn(position: Int) {
            val item = getItem(position) ?: return
            binding.root.setOnClickListener { result.onClickListener(item.id) }
        }
    }

    interface Result {
        fun onClickListener(id: Int)
    }
}

class AnimeCallBack : DiffUtil.ItemCallback<Anime>() {
    override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem == newItem
    }
}
