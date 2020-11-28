package app.usersapp.ui.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import app.usersapp.BR
import app.usersapp.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class AppBaseActivity<B : ViewDataBinding, V : ActivityBaseViewModel> :
    DaggerAppCompatActivity() {
    protected lateinit var viewModel: V
    protected lateinit var dataBinding: B
    abstract fun provideViewModelClass(): Class<V>
    private lateinit var progressAlertDialog: AlertDialog

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[provideViewModelClass()]
        dataBinding = DataBindingUtil.setContentView(this, layoutId)
        dataBinding.setVariable(BR.viewModel, viewModel)
        dataBinding.executePendingBindings()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        listenLiveData()
    }

    open fun listenLiveData() {
        viewModel.showMessage.observe(this, {
            it?.let { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
        })
        viewModel.loadingLiveData.observe(this, {
            if (it)
                showLoading()
            else hideLoading()
        })
    }

    override fun onDestroy() {
        removeObserver()
        dataBinding.unbind()
        super.onDestroy()
    }

    open fun removeObserver() {
        viewModel.showMessage.removeObservers(this)
        viewModel.loadingLiveData.removeObservers(this)
    }

    /**
     * Method call to show loading
     */
    private fun showLoading() {
        hideLoading()
        if (!::progressAlertDialog.isInitialized) {
            val dialogBuilder = AlertDialog.Builder(this)
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
            val dialogView = inflater?.inflate(R.layout.dialog_progress, null)
            dialogBuilder.setView(dialogView)
            dialogBuilder.setCancelable(false)
            progressAlertDialog = dialogBuilder.create()
            progressAlertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
        progressAlertDialog.show()
    }

    /**
     * Method call to hide loading
     */
    private fun hideLoading() {
        if (::progressAlertDialog.isInitialized)
            progressAlertDialog.dismiss()
    }
}