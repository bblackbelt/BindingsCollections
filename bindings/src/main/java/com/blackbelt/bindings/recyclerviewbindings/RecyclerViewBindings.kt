package com.blackbelt.bindings.recyclerviewbindings

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.blackbelt.bindings.BaseBindableRecyclerView


private val KEY_ITEMS = -1024

@BindingAdapter("itemViewBinder")
fun setItemViewBinder(recyclerView: androidx.recyclerview.widget.RecyclerView,
                      itemViewMapper: Map<Class<*>, AndroidItemBinder>) {
    setItemViewBinder(recyclerView, itemViewMapper, true)
}

@BindingAdapter("itemViewBinder", "nestedScrollingEnabled")
fun setItemViewBinder(recyclerView: androidx.recyclerview.widget.RecyclerView, itemViewMapper: Map<Class<*>, AndroidItemBinder>,
                      nestedScrollingEnabled: Boolean) {
    val items = recyclerView.getTag(KEY_ITEMS) as List<Any>?
    if (recyclerView.adapter is AndroidBindableRecyclerViewAdapter) {
        (recyclerView.adapter as AndroidBindableRecyclerViewAdapter).setDataSet(items)
        return
    }
    val adapter = AndroidBindableRecyclerViewAdapter(itemViewMapper)
    recyclerView.isNestedScrollingEnabled = nestedScrollingEnabled
    recyclerView.setHasFixedSize(true)
    recyclerView.adapter = adapter
}

@BindingAdapter("nestedScrollingEnabled")
fun setNestedScrollingEnabled(recyclerView: androidx.recyclerview.widget.RecyclerView, nestedScrollingEnabled: Boolean) {
    recyclerView.isNestedScrollingEnabled = nestedScrollingEnabled
}

@BindingAdapter("items")
fun setItems(recyclerView: androidx.recyclerview.widget.RecyclerView, items: List<Any>?) {
    recyclerView.setTag(KEY_ITEMS, items)
    if (recyclerView.adapter is AndroidBindableRecyclerViewAdapter) {
        (recyclerView.adapter as AndroidBindableRecyclerViewAdapter).setDataSet(items)
    }
}

@BindingAdapter("onItemClickListener")
fun setOnItemClickListener(recyclerView: BaseBindableRecyclerView, clickListener: ItemClickListener?) {
    recyclerView.setOnItemClickListener(clickListener)
}

@BindingAdapter("layoutManager")
fun <T> setLayoutManager(recyclerView: androidx.recyclerview.widget.RecyclerView,
                         layoutManager: LayoutManagers.LayoutManagerFactory) {
    val manager = layoutManager.create(recyclerView)
    recyclerView.layoutManager = manager
}

@BindingAdapter("itemDecoration")
fun addDividerItemDecoration(recyclerView: androidx.recyclerview.widget.RecyclerView, itemDecoration: androidx.recyclerview.widget.RecyclerView.ItemDecoration?) {
    itemDecoration?.let {
        recyclerView.addItemDecoration(it)
    }
}

@BindingAdapter(value = "pageDescriptorAttrChanged")
fun setListener(recyclerView: AndroidBindableRecyclerView, listener: InverseBindingListener?) {
    if (listener != null) {
        recyclerView.setOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageChangeListener(pageDescriptor: PageDescriptor) {
                listener.onChange()
            }
        })
    }
}

@BindingAdapter("pageDescriptor")
fun setPageDescriptor(recyclerView: AndroidBindableRecyclerView, pageDescriptor: PageDescriptor?) {
    if (recyclerView.pageDescriptor != pageDescriptor) {
        recyclerView.pageDescriptor = pageDescriptor
    }
}

@InverseBindingAdapter(attribute = "pageDescriptor")
fun getPageDescriptor(recyclerView: AndroidBindableRecyclerView): PageDescriptor? {
    return recyclerView.pageDescriptor
}

@BindingAdapter("snapHelper")
fun setSnapHelper(recyclerView: AndroidBindableRecyclerView, snapHelper: androidx.recyclerview.widget.SnapHelper) {
    recyclerView.setSnapHelper(snapHelper)
}