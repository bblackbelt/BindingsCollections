package com.bblackbelt.githubusers.repository.users

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.blackbelt.bindings.paging.NetworkState
import com.blackbelt.bindings.paging.PagedItem

data class ViewWrapper<T : PagedItem>(
        val pagedList: LiveData<PagedList<T>>,
        val networkState: LiveData<NetworkState>,
        val retry: () -> Unit)