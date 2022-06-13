package com.tmob.casestudy.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmob.casestudy.R
import com.tmob.casestudy.databinding.ItemUserBinding
import com.tmob.casestudy.model.UserListResponseItem
import com.tmob.casestudy.view.UserListFragment
import com.tmob.casestudy.view.UserListFragmentDirections


class UserListAdapter() :
    ListAdapter<UserListResponseItem, UserListAdapter.ViewHolder>(ARTICLE_DIFF_CALLBACK) {
    private var searchList = mutableListOf<UserListResponseItem>()

    private var unfilteredList = mutableListOf<UserListResponseItem>()

    inner class ViewHolder(private var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserListResponseItem) {
            binding.user = item
            binding.root.setOnClickListener {
                val fragment: UserListFragment = binding.root.findFragment<UserListFragment>()
                val ar = UserListFragmentDirections.actionUserListFragmentToUserDetailFragment(item)
                fragment.findNavController().navigate(ar)
            }
        }

    }

    fun filter(query: String) {
        searchList = mutableListOf<UserListResponseItem>()
        unfilteredList.forEach {

            if (it.login.contains(query)==true) {
                searchList.add(it)
            }
        }

            submitList(searchList)
        notifyDataSetChanged()
    }

    fun addUsersToList(users: MutableList<UserListResponseItem>) {
        submitList(users)
        unfilteredList = currentList

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListAdapter.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemUserBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_user, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
    }


    companion object {
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserListResponseItem>() {
            override fun areItemsTheSame(
                oldItem: UserListResponseItem,
                newItem: UserListResponseItem
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UserListResponseItem,
                newItem: UserListResponseItem
            ): Boolean =
                oldItem == newItem
        }
    }
}