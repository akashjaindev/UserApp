package app.usersapp.ui.main

import android.os.Bundle
import app.usersapp.R
import app.usersapp.databinding.ActivityMainBinding
import app.usersapp.ui.base.AppBaseActivity

class MainActivity : AppBaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun provideViewModelClass() = MainViewModel::class.java
    override val layoutId = R.layout.activity_main

    override fun listenLiveData() {
        super.listenLiveData()
        viewModel.userListData.observe(this, {
            viewModel.adapter.submitList(it)
            dataBinding.refreshLayout.isRefreshing=false
        })
    }

    override fun removeObserver() {
        super.removeObserver()
        viewModel.userListData.removeObservers(this)
    }

}