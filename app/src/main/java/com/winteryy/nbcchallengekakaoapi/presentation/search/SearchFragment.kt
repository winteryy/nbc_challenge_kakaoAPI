package com.winteryy.nbcchallengekakaoapi.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.winteryy.nbcchallengekakaoapi.databinding.FragmentSearchBinding
import com.winteryy.nbcchallengekakaoapi.presentation.MainViewModel
import com.winteryy.nbcchallengekakaoapi.presentation.adapter.ImageItemAdapter

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: MainViewModel by activityViewModels()
    private val adapter by lazy {
        ImageItemAdapter {
            sharedViewModel.updateItem(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchRV.adapter = adapter
        sharedViewModel.searchList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        sharedViewModel.searchImage("코틀린")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}