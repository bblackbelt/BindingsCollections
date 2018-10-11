package com.blackbelt.bindings.recyclerviewbindings

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import com.blackbelt.bindings.BaseBindableRecyclerView


class AndroidBindableRecyclerView(context: Context, attrs: AttributeSet?) : BaseBindableRecyclerView(context, attrs) {

    private var pageScrollListener: PageScrollListener? = null

    private var pageListener: OnPageChangeListener? = null

    var pageDescriptor: PageDescriptor? = null
        set(pageDescriptor) {
            if (pageScrollListener != null) {
                removeOnScrollListener(pageScrollListener)
            }
            field = pageDescriptor
            pageScrollListener = PageScrollListener()
            if (pageListener != null) {
                pageScrollListener?.pageChangeListener = pageListener
            }
            pageScrollListener?.updatePageDescriptor(field)
            addOnScrollListener(pageScrollListener)
        }

    private class PageScrollListener : RecyclerView.OnScrollListener() {

        private var pageDescriptor: PageDescriptor? = null

        fun updatePageDescriptor(value: PageDescriptor?) {
            if (value === pageDescriptor) {
                return
            }
            pageDescriptor = value
            if (value != null && !first && pageChangeListener != null) {
                pageChangeListener?.onPageChangeListener(value)
                first = true
            }
        }

        private var first = false

        var pageChangeListener: OnPageChangeListener? = null
            set(value) {
                field = value
                if (value != null && pageDescriptor != null && !first) {
                    field?.onPageChangeListener(pageDescriptor ?: return)
                    first = true
                }
            }

        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            val pageDescriptor: PageDescriptor = pageDescriptor ?: return
            val layoutManager: LayoutManager = recyclerView?.layoutManager ?: return
            val totalItemCount = layoutManager.itemCount
            val lastVisibleItem = getLastVisibleItemPosition(layoutManager)
            if (totalItemCount - lastVisibleItem <= pageDescriptor.getThreshold()) {
                if (pageDescriptor.getCurrentPage() < 1 + totalItemCount / pageDescriptor.getPageSize()) {
                    pageDescriptor.setCurrentPage(1 + totalItemCount / pageDescriptor.getPageSize())
                    pageChangeListener?.onPageChangeListener(pageDescriptor)
                }
            }
        }

        private fun getLastVisibleItemPosition(layoutManager: RecyclerView.LayoutManager): Int {
            if (layoutManager is LinearLayoutManager) {
                return layoutManager.findLastVisibleItemPosition()
            } else {
                throw UnsupportedOperationException()
            }
        }
    }

    fun setOnPageChangeListener(pageChangeListener: OnPageChangeListener) {
        if (pageScrollListener != null) {
            pageScrollListener?.pageChangeListener = pageChangeListener
        } else {
            pageListener = pageChangeListener
        }
    }

    override fun getDataSet(): List<Any>? =
            (adapter as? AndroidBindableRecyclerViewAdapter)?.dataSet?.currentList

    override fun getItemAtPosition(position: Int): Any? = getDataSet()?.getOrNull(position)
}
