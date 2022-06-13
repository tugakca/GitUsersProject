package com.tmob.casestudy.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmob.casestudy.databinding.FragmentListBinding
import com.tmob.casestudy.model.UserListResponse
import com.tmob.casestudy.view.adapter.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var binding: FragmentListBinding
    private lateinit var userList: UserListResponse

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.getUserData()
        observeData()
        if (!::userListAdapter.isInitialized)
            userListAdapter = UserListAdapter()
        binding.bindAdapter(articleAdapter = userListAdapter)
        setSearchBar()
    }

    private fun setSearchBar() {
        binding.search.setOnCloseListener(object :
            androidx.appcompat.widget.SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                sharedViewModel.getUserData()
                return false
            }

        })
        binding.search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    filter(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    filter(it)
                }
                return false
            }
        })
    }

    private fun filter(value: String) {
        val searchList = UserListResponse()
        userList.forEach {
            if (it.login.contains(value)) {
                searchList.add(it)
            }
        }
        userListAdapter.submitList(searchList)

    }

    private fun observeData() {
        sharedViewModel.userListResponse.observe(requireActivity()) {
            val sharedValue = sharedPreferences.getString("login", "")
            if (!sharedValue.isNullOrEmpty()) {
                it.forEach { response ->
                    response.isDetailShowed = response.login == sharedValue
                }
            }
            userListAdapter.addUsersToList(it)
            userList = it
        }
    }

}

private fun FragmentListBinding.bindAdapter(articleAdapter: UserListAdapter) {
    userRv.adapter = articleAdapter
    userRv.layoutManager = LinearLayoutManager(userRv.context)
    val decoration = DividerItemDecoration(userRv.context, DividerItemDecoration.VERTICAL)
    userRv.addItemDecoration(decoration)
}