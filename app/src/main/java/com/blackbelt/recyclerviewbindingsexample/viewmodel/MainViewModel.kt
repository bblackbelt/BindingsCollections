package com.blackbelt.recyclerviewbindingsexample.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.res.Resources
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.blackbelt.bindings.notifications.BundleParams
import com.blackbelt.bindings.notifications.ClickItemWrapper
import com.blackbelt.bindings.notifications.MessageWrapper
import com.blackbelt.bindings.recyclerviewbindings.AndroidItemBinder
import com.blackbelt.bindings.recyclerviewbindings.ItemClickListener
import com.blackbelt.bindings.viewmodel.BaseViewModel
import com.blackbelt.recyclerviewbindingsexample.BR
import com.blackbelt.recyclerviewbindingsexample.R

class MainViewModel(resources: Resources) : BaseViewModel() {

    val items: MutableLiveData<MutableList<ItemViewModel>> = MutableLiveData()

    val binders: Map<Class<*>, AndroidItemBinder> =
            hashMapOf(ItemViewModel::class.java to AndroidItemBinder(R.layout.item_layout, BR.itemViewModel))

    private val mResources = resources

    init {
        val items = mutableListOf<ItemViewModel>()
        for (i in 1..100) {
            items.add(ItemViewModel(i.toString()))
        }
        this.items.postValue(items)
        sendMessageEvent(MessageWrapper.withSnackBar(R.string.item_generation_complete))
    }

    fun getItemClickListener() = object : ItemClickListener {
        override fun onItemClicked(view: View, item: Any?) {
            val listItem = item as? ItemViewModel ?: return
            sendMessageEvent(MessageWrapper.withSnackBar(listItem.name))
            val params = BundleParams()
            params.putString("TEST", listItem.name ?: "")
            sendCommandEvent(ClickItemWrapper(0, params))
            item.setName("test${item.name}")
        }
    }

    class Factory(resources: Resources) : ViewModelProvider.NewInstanceFactory() {

        private val mResources = resources

        override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(mResources) as T
    }
}