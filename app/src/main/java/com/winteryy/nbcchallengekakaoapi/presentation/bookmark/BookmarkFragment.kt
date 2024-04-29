package com.winteryy.nbcchallengekakaoapi.presentation.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.winteryy.nbcchallengekakaoapi.databinding.FragmentBookmarkBinding
import com.winteryy.nbcchallengekakaoapi.presentation.MainViewModel
import com.winteryy.nbcchallengekakaoapi.presentation.adapter.ImageItemAdapter

class BookmarkFragment: Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: MainViewModel by activityViewModels()
    private val adapter by lazy {
        ImageItemAdapter {
            sharedViewModel.updateItem(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bookmarkRV.adapter = adapter
        sharedViewModel.bookmarkList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}