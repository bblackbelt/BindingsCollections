package com.blackbelt.bindings.activity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.blackbelt.bindings.notifications.ClickItemWrapper
import com.blackbelt.bindings.notifications.MessageDelegate
import com.blackbelt.bindings.notifications.Params
import com.blackbelt.bindings.viewmodel.BaseViewModel

abstract class BaseBindingActivity : AppCompatActivity() {

    private var mViewModel: BaseViewModel? = null

    private val mMessageDelegate: MessageDelegate by lazy {
        ViewModelProviders.of(this).get(MessageDelegate::class.java)
    }

    private var mDataBinding: ViewDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMessageDelegate.setActivity(this)
    }

    fun setContentView(@LayoutRes layoutId: Int, brVariable: Int, viewModel: BaseViewModel) {
        mViewModel = viewModel
        mViewModel?.getMessageDelegate()?.observe(this, Observer { t -> mMessageDelegate.showMessage(t) })
        mViewModel?.getItemClickDelegate()?.observe(this, Observer { t -> this.handleClick(t) })

        mDataBinding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
        mDataBinding?.setVariable(brVariable, viewModel)
        mDataBinding?.setLifecycleOwner(this)
        super.setContentView(mDataBinding?.root)
    }

    private fun handleClick(item: ClickItemWrapper<Params>?) {
        val itemClicked = item ?: return
        onItemClicked(itemClicked)
    }

    protected open fun onItemClicked(itemWrapper: ClickItemWrapper<Params>) {}

    override fun onDestroy() {
        super.onDestroy()
        mDataBinding?.let {
            it.setLifecycleOwner(null)
        }
    }
}