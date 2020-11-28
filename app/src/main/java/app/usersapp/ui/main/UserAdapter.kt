package app.usersapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.usersapp.databinding.AdapterUserBinding
import app.usersapp.modal.UserData


class UserAdapter(val viewModel: MainViewModel) : PagedListAdapter<UserData, UserAdapter.UserViewModel>(UserItemDiffUtil()) {

    class UserViewModel(val binding:AdapterUserBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewModel(AdapterUserBinding.inflate(LayoutInflater.from(parent.context),parent,false).apply {
        viewModel=this@UserAdapter.viewModel
    })

    override fun onBindViewHolder(holder: UserViewModel, position: Int) {
        holder.binding.data=getItem(position)
        holder.binding.executePendingBindings()
    }
}