package com.blackbelt.bindings.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blackbelt.bindings.recyclerviewbindings.AndroidItemBinder

@BindingAdapter("submitList")
fun <T> submitList(view: PagedBindableRecyclerView, items: LiveData<PagedList<T>>?) where T : PagedItem {
    view.submitList(items?.value)
}

@BindingAdapter("objectTemplates")
fun <T : PagedItem> setItemViewBinder(recyclerView: androidx.recyclerview.widget.RecyclerView, itemViewMapper: Map<Class<*>, AndroidItemBinder>) {
    if (recyclerView.adapter != null) {
        return
    }
    val adapter = PagedBindableRecyclerViewAdapter<T>(itemViewMapper)
    recyclerView.adapter = adapter
}

@BindingAdapter("networkState")
fun setNetworkState(recyclerView: androidx.recyclerview.widget.RecyclerView, networkState: LiveData<NetworkState>?) {
    (recyclerView.adapter as? PagedBindableRecyclerViewAdapter<*>)
            ?.setNetworkState(networkState?.value)
}

@BindingAdapter("networkStateLayout")
fun setNetworkStateLayout(recyclerView: androidx.recyclerview.widget.RecyclerView, networkState: Int?) {
    (recyclerView.adapter as? PagedBindableRecyclerViewAdapter<*>)
            ?.setNetworkStateLayout(networkState)
}