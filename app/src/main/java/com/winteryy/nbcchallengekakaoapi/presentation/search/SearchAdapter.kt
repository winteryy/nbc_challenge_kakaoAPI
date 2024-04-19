package com.winteryy.nbcchallengekakaoapi.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.winteryy.nbcchallengekakaoapi.databinding.ItemListBinding
import com.winteryy.nbcchallengekakaoapi.presentation.model.ImageListItem

class SearchAdapter: ListAdapter<ImageListItem, SearchAdapter.SearchViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class SearchViewHolder(
        private val binding: ItemListBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ImageListItem) {
            binding.itemImageView.load(item.thumbnailUrl)
            binding.itemTitleTextView.text = item.siteName.ifBlank { "이름 없음" }
            binding.bookmarkSwitch.isChecked = item.isBookmarked
        }

    }

    companion object {
        private val DIFF_UTIL = object: DiffUtil.ItemCallback<ImageListItem>() {
            override fun areItemsTheSame(oldItem: ImageListItem, newItem: ImageListItem): Boolean {
                return oldItem.imageUrl==newItem.imageUrl
            }

            override fun areContentsTheSame(
                oldItem: ImageListItem,
                newItem: ImageListItem
            ): Boolean {
                return oldItem==newItem
            }

        }
    }
}