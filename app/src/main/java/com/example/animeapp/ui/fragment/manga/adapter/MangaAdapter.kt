package com.example.animeapp.ui.fragment.manga.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.animeapp.databinding.ItemMangaBinding
import com.example.domain.manga.entity.Manga

class MangaAdapter(private val result: Result) :
    PagingDataAdapter<Manga, MangaAdapter.MangaViewHolder>(MangaCallBack()) {
    override fun onBindViewHolder(holder: MangaViewHolder, position: Int) {
        holder.onBind(position)
        holder.initBtn(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MangaViewHolder =
        MangaViewHolder(
            ItemMangaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    inner class MangaViewHolder(
        private val binding: ItemMangaBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val item = getItem(position) ?: return
            with(binding) {
                itemImage.load(item.image)
                itemMangaTitle.text = item.title
                itemMangaAuthor.text = item.author
            }
        }

        fun initBtn(position: Int) {
            val item = getItem(position) ?: return
            itemView.setOnClickListener { result.onClickListener(item.id) }
        }
    }

    interface Result {
        fun onClickListener(id: Int)
    }
}

class MangaCallBack : DiffUtil.ItemCallback<Manga>() {
    override fun areItemsTheSame(oldItem: Manga, newItem: Manga): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Manga, newItem: Manga): Boolean {
        return oldItem == newItem
    }
}