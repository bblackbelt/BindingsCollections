package com.blackbelt.bindings.viewmodel

import android.os.Looper
import androidx.lifecycle.ViewModel
import com.blackbelt.bindings.notifications.ClickItemWrapper
import com.blackbelt.bindings.notifications.MessageWrapper
import com.blackbelt.bindings.notifications.Params
import com.blackbelt.bindings.utils.SingleLiveEvent


open class BaseViewModel : ViewModel() {

    private val mMessageNotifier = SingleLiveEvent<MessageWrapper>()

    private var mItemClickNotifier: SingleLiveEvent<ClickItemWrapper<Params>> = SingleLiveEvent()

    open fun handlerError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    open fun getMessageDelegate() = mMessageNotifier

    open fun getItemClickDelegate() = mItemClickNotifier

    fun sendCommandEvent(itemWrapper: ClickItemWrapper<Params>) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            mItemClickNotifier.value = itemWrapper
        } else {
            mItemClickNotifier.postValue(itemWrapper)
        }
    }

    fun sendMessageEvent(wrapper: MessageWrapper) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            mMessageNotifier.value = wrapper
        } else {
            mMessageNotifier.postValue(wrapper)
        }
    }

}