package com.tmob.casestudy.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.tmob.casestudy.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {
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