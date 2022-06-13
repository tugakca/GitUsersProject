package com.tmob.casestudy.view

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
import com.tmob.casestudy.model.UserListResponseItem
import com.tmob.casestudy.view.adapter.UserListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var userListAdapter: UserListAdapter
    private lateinit var binding: FragmentListBinding
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
        userListAdapter = UserListAdapter()
        binding.bindAdapter(articleAdapter = userListAdapter)
      //  setData()

        binding.search.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                query?.let {
                    userListAdapter.filter(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    userListAdapter.filter(it)
                }
                return false
            }

        })
    }

    private fun setData() {
        val user = UserListResponseItem(
            "https://avatars.githubusercontent.com/u/2?v=4",
            "dfvsd",
            "sddsfv",
            "fvsdv",
            "dfvdsfv",
            "fbsfb",
            "dfvsd",
            1234,
            "tugrul",
            "",
            "fdv",
            "dfv",
            "dfv",
            false,
            "fvds",
            "dfvfsv",
            "dfvd",
            "sdvsdv",
            20,
            10
        )
        val list = UserListResponse()
        list.add(user)
        userListAdapter.addUsersToList(list)

    }

    private fun observeData() {
        sharedViewModel.userListResponse.observe(requireActivity()) {
            userListAdapter.addUsersToList(it)
        }
    }

}

private fun FragmentListBinding.bindAdapter(articleAdapter: UserListAdapter) {
    userRv.adapter = articleAdapter
    userRv.layoutManager = LinearLayoutManager(userRv.context)
    val decoration = DividerItemDecoration(userRv.context, DividerItemDecoration.VERTICAL)
    userRv.addItemDecoration(decoration)
}