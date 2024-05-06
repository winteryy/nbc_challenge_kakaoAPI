package com.winteryy.nbcchallengekakaoapi.presentation.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.winteryy.nbcchallengekakaoapi.databinding.FragmentBookmarkBinding
import com.winteryy.nbcchallengekakaoapi.presentation.shared.SearchSharedEvent
import com.winteryy.nbcchallengekakaoapi.presentation.shared.SearchSharedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BookmarkFragment: Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private val searchSharedViewModel: SearchSharedViewModel by activityViewModels()
    private val adapter by lazy {
        BookmarkRvAdapter()
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

        viewLifecycleOwner.lifecycleScope.launch {
            bookmarkViewModel.uiState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest {
                    onBind(it)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            searchSharedViewModel.event.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest {
                    onSharedEvent(it)
                }
        }

    }

    private fun onBind(state: BookmarkListUiState) {
        adapter.submitList(state.list)
    }

    private fun onSharedEvent(event: SearchSharedEvent) {
        when(event) {
            is SearchSharedEvent.UpdateBookmark -> bookmarkViewModel.updateBookmark(event.list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = BookmarkFragment()
    }
}