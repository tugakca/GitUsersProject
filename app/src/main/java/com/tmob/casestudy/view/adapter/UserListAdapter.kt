package com.tmob.casestudy.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmob.casestudy.R
import com.tmob.casestudy.databinding.ItemUserBinding
import com.tmob.casestudy.model.UserResponse
import com.tmob.casestudy.model.UserResponseItem


class UserListAdapter() :
    ListAdapter<UserResponseItem, UserListAdapter.ViewHolder>(ARTICLE_DIFF_CALLBACK) {
    private lateinit var searchList: MutableList<UserResponseItem>
    private lateinit var tempList: MutableList<UserResponseItem>
    private lateinit var unfilteredList: MutableList<UserResponseItem>

    inner class ViewHolder(private var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserResponseItem) {
            binding.user = item
        }

    }




    fun filter(query: String) {
        if (::searchList.isInitialized)
            searchList.clear()
        searchList= UserResponse()
        unfilteredList.forEach {
            if (it.login.contains(query)) {
                searchList.add(it)
            }
        }
        if (searchList.size > 0)
            submitList(searchList)
        notifyDataSetChanged()
    }
    fun addUsersToList(users:MutableList<UserResponseItem>) {
        submitList(users)
        if(::unfilteredList.isInitialized)
            unfilteredList.clear()
        unfilteredList=currentList


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
        private val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserResponseItem>() {
            override fun areItemsTheSame(
                oldItem: UserResponseItem,
                newItem: UserResponseItem
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UserResponseItem,
                newItem: UserResponseItem
            ): Boolean =
                oldItem == newItem
        }
    }
}