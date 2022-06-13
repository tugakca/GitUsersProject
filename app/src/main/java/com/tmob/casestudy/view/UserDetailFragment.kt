package com.tmob.casestudy.view

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.tmob.casestudy.databinding.FragmentUserDetailBinding
import com.tmob.casestudy.model.UserDetailResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    @Inject
    lateinit var  sharedPreferences: SharedPreferences

    private val sharedViewModel: MainViewModel by activityViewModels()
    val args: UserDetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentUserDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.user = UserDetailResponse()
        sharedPreferences.edit().putString("login",args.userItem.login).commit()
        requests()
        observeData()
    }

    private fun requests() {
        sharedViewModel.getUserDetail(args.userItem.login)
    }

    private fun observeData() {

        sharedViewModel.userDetailResponse.observe(requireActivity()) {
            binding.user = it
            Log.i("tg", "it.size.toString()")
        }

    }

}