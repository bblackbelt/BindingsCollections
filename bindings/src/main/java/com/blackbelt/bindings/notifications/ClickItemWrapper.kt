package com.blackbelt.bindings.notifications

data class ClickItemWrapper<T> constructor(val clickedItemId: Int, val additionalData: T? = null) where T : Params