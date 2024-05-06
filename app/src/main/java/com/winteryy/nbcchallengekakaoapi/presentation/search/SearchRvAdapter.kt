package com.winteryy.nbcchallengekakaoapi.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.winteryy.nbcchallengekakaoapi.databinding.ItemImageBinding
import com.winteryy.nbcchallengekakaoapi.databinding.ItemVideoBinding

class SearchRvAdapter(
    private val onSwitchChecked: (SearchListItem) -> Unit
): ListAdapter<SearchListItem, SearchRvAdapter.BaseSearchListViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSearchListViewHolder {
        return when(viewType) {
            SearchRvViewType.IMAGE.ordinal -> SearchImageItemViewHolder(
                ItemImageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            SearchRvViewType.VIDEO.ordinal -> SearchVideoItemViewHolder(
                ItemVideoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid View Type")
        }
    }

    override fun onViewRecycled(holder: BaseSearchListViewHolder) {
        holder.removeSwitchListener()
        super.onViewRecycled(holder)

    }

    override fun onBindViewHolder(holder: BaseSearchListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is SearchListItem.SearchImageItem -> SearchRvViewType.IMAGE.ordinal
            is SearchListItem.SearchVideoItem -> SearchRvViewType.VIDEO.ordinal
        }
    }

    enum class SearchRvViewType {
        IMAGE, VIDEO
    }

    abstract class BaseSearchListViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view) {
        abstract fun onBind(item: SearchListItem)
        abstract fun removeSwitchListener()
    }

    inner class SearchImageItemViewHolder(
        private val binding: ItemImageBinding
    ): BaseSearchListViewHolder(binding.root) {
        override fun onBind(item: SearchListItem) {
            binding.apply {
                itemImageView.load(item.thumbnailUrl)
                itemTitleTextView.text = item.title.ifBlank { "이름 없음" }
                bookmarkSwitch.isChecked = item.isBookmarked
                bookmarkSwitch.setOnCheckedChangeListener { _, _ ->
                    if(bookmarkSwitch.isChecked != item.isBookmarked) {
                        onSwitchChecked(item)
                    }
                }
            }
        }

        override fun removeSwitchListener() {
            binding.bookmarkSwitch.setOnCheckedChangeListener(null)
        }
    }

    inner class SearchVideoItemViewHolder(
        private val binding: ItemVideoBinding
    ): BaseSearchListViewHolder(binding.root) {
        override fun onBind(item: SearchListItem) {
            binding.apply {
                itemImageView.load(item.thumbnailUrl)
                itemTitleTextView.text = item.title.ifBlank { "이름 없음" }
                bookmarkSwitch.isChecked = item.isBookmarked
                bookmarkSwitch.setOnCheckedChangeListener { _, _ ->
                    if(bookmarkSwitch.isChecked != item.isBookmarked) {
                        onSwitchChecked(item)
                    }
                }
            }
        }

        override fun removeSwitchListener() {
            binding.bookmarkSwitch.setOnCheckedChangeListener(null)
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<SearchListItem>() {
            override fun areItemsTheSame(
                oldItem: SearchListItem,
                newItem: SearchListItem
            ): Boolean {
                return oldItem.thumbnailUrl==newItem.thumbnailUrl
            }

            override fun areContentsTheSame(
                oldItem: SearchListItem,
                newItem: SearchListItem
            ): Boolean {
                return oldItem==newItem
            }

        }
    }
}