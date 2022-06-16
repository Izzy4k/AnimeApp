package com.example.animeapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.animeapp.databinding.ItemLoadBinding

typealias  TryAgainAction = () -> Unit

class DefaultLoadAdapter(
    private val tryAgainAction: TryAgainAction
) : LoadStateAdapter<DefaultLoadAdapter.DefaultViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): DefaultViewHolder =
        DefaultViewHolder(
            ItemLoadBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: DefaultViewHolder, loadState: LoadState) {
        holder.onBind(loadState)
        holder.initBtn()
    }

    inner class DefaultViewHolder(private val binding: ItemLoadBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(loadState: LoadState) {
            with(binding) {
                itemTxtMessage.isVisible = loadState is LoadState.Error
                itemProgress.isVisible = loadState is LoadState.Loading
                itemBtnTryAgain.isVisible = loadState is LoadState.Error
            }
        }

        fun initBtn() {
            binding.itemBtnTryAgain.setOnClickListener { tryAgainAction() }
        }
    }
}