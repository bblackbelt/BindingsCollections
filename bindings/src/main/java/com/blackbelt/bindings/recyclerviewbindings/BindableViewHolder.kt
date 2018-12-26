package com.blackbelt.bindings.recyclerviewbindings

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindableViewHolder(viewDataBinding: ViewDataBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(viewDataBinding.root) {
    val mViewDataBinding = viewDataBinding
}