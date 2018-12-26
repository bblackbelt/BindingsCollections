package com.blackbelt.bindings.paging

import androidx.recyclerview.widget.DiffUtil

class PagedItemSourceDiffCallback<T> : DiffUtil.ItemCallback<T>() where T : Any {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}
