package com.blackbelt.bindings

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import com.blackbelt.bindings.recyclerviewbindings.ItemClickListener
import com.blackbelt.bindings.recyclerviewbindings.RecyclerViewGestureListener

abstract class BaseBindableRecyclerView(context: Context, attrs: AttributeSet?)
    : androidx.recyclerview.widget.RecyclerView(context, attrs), androidx.recyclerview.widget.RecyclerView.OnItemTouchListener {

    private var mSnapHelper: androidx.recyclerview.widget.SnapHelper? = null

    private var mRecyclerViewGestureListener: RecyclerViewGestureListener? = null

    private var mGestureDetector: GestureDetector? = null

    fun setOnItemClickListener(l: ItemClickListener?) {
        if (l == null) {
            return
        }
        mRecyclerViewGestureListener = RecyclerViewGestureListener(this)
        mGestureDetector = GestureDetector(context, mRecyclerViewGestureListener)
        mRecyclerViewGestureListener!!.setRecyclerViewClickListener(l)
    }

    override fun onInterceptTouchEvent(rv: androidx.recyclerview.widget.RecyclerView, e: MotionEvent): Boolean {
        return mGestureDetector?.onTouchEvent(e) ?: false
    }

    override fun onTouchEvent(rv: androidx.recyclerview.widget.RecyclerView, e: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addOnItemTouchListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeOnItemTouchListener(this)
    }

    abstract fun getDataSet(): List<Any>?

    abstract fun getItemAtPosition(position: Int): Any?

    fun setSnapHelper(snapHelper: androidx.recyclerview.widget.SnapHelper) {
        if (mSnapHelper != null) {
            mSnapHelper?.attachToRecyclerView(null)
        }
        mSnapHelper = snapHelper
        mSnapHelper?.attachToRecyclerView(this)
    }
}