package com.example.animeapp.ui.fragment.manga.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.example.animeapp.databinding.ItemSliderBinding
import com.example.domain.manga.entity.Manga
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderMangaAdapter(private val list: List<Manga>) :
    SliderViewAdapter<SliderMangaAdapter.SliderViewHolder>() {
    inner class SliderViewHolder(private val binding: ItemSliderBinding) :
        ViewHolder(binding.root) {
        fun onBind(position: Int) {
            binding.scaleImage.load(list[position].image)
        }
    }

    override fun getCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder = SliderViewHolder(
        ItemSliderBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
    )

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        viewHolder?.onBind(position)
    }
}