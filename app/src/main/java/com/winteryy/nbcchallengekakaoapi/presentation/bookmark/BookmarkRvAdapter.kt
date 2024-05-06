package com.winteryy.nbcchallengekakaoapi.presentation.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.winteryy.nbcchallengekakaoapi.databinding.ItemBookmarkBinding

class BookmarkRvAdapter
    : ListAdapter<BookmarkListItem, BookmarkRvAdapter.BookmarkItemViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkItemViewHolder {
        return BookmarkItemViewHolder(
            ItemBookmarkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookmarkItemViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class BookmarkItemViewHolder(
        private val binding: ItemBookmarkBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: BookmarkListItem) {
            binding.apply {
                itemImageView.load(item.thumbnailUrl)
                itemTitleTextView.text = item.title.ifBlank { "이름 없음" }
                bookmarkSwitch.isChecked = item.isBookmarked
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<BookmarkListItem>() {
            override fun areItemsTheSame(
                oldItem: BookmarkListItem,
                newItem: BookmarkListItem
            ): Boolean {
                return oldItem.thumbnailUrl==newItem.thumbnailUrl
            }

            override fun areContentsTheSame(
                oldItem: BookmarkListItem,
                newItem: BookmarkListItem
            ): Boolean {
                return oldItem==newItem
            }

        }
    }
}