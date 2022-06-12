package com.tmob.casestudy.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmob.casestudy.R
import com.tmob.casestudy.databinding.ItemUserBinding
import com.tmob.casestudy.model.UserResponseItem


class UserListAdapter() :
ListAdapter<UserResponseItem,UserListAdapter.ViewHolder>(ARTICLE_DIFF_CALLBACK)
    {




    inner class ViewHolder(private var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserResponseItem) {
            binding.user = item
        }

    }

/*
    fun addUsersToList(users: UserResponse) {
        if (::userList.isInitialized)
            userList.clear()
        userList = UserResponse()
        userList.addAll(users)
        notifyDataSetChanged()
    }*/

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
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserResponseItem>() {
            override fun areItemsTheSame(oldItem: UserResponseItem, newItem: UserResponseItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserResponseItem, newItem: UserResponseItem): Boolean =
                oldItem == newItem
        }
    }
}