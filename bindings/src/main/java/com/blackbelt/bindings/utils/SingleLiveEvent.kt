package com.blackbelt.bindings.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mHasPendingChanges = AtomicBoolean()

    private val mHandler = Handler(Looper.getMainLooper())

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        if (Looper.getMainLooper() != Looper.myLooper()) {
            mHandler.post {
                super.observe(owner, Observer<T> { t ->
                    if (mHasPendingChanges.compareAndSet(true, false)) {
                        observer.onChanged(t)
                    }
                })
            }
        } else {
            super.observe(owner, Observer<T> { t ->
                if (mHasPendingChanges.compareAndSet(true, false)) {
                    observer.onChanged(t)
                }
            })
        }
    }

    override fun setValue(value: T?) {
        mHasPendingChanges.set(true)
        super.setValue(value)
    }

    @MainThread
    fun call() {
        value = null
    }
}