package com.blackbelt.bindings.paging

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class PagedBindableViewHolder(val viewDataBinding: ViewDataBinding)
    : androidx.recyclerview.widget.RecyclerView.ViewHolder(viewDataBinding.root)