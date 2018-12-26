package com.bblackbelt.githubusers.view.users

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import android.graphics.Rect
import android.os.Bundle
import com.bblackbelt.githubusers.BR
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.bblackbelt.githubusers.R
import com.bblackbelt.githubusers.view.users.viewmodel.UsersViewModel
import com.blackbelt.bindings.activity.BaseBindingActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class UsersActivity : BaseBindingActivity() {

    @Inject
    lateinit var mFactory: UsersViewModel.Factory

    private val mViewModel: UsersViewModel by lazy {
        ViewModelProviders.of(this, mFactory)[UsersViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main, BR.usersViewModel, mViewModel)
    }
}