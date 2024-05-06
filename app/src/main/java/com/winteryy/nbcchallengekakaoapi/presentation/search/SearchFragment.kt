package com.winteryy.nbcchallengekakaoapi.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.winteryy.nbcchallengekakaoapi.databinding.FragmentSearchBinding
import com.winteryy.nbcchallengekakaoapi.presentation.shared.SearchSharedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchSharedViewModel: SearchSharedViewModel by activityViewModels()
    private val searchViewModel: SearchViewModel by viewModels {
        SearchViewModelFactory()
    }

    private val adapter by lazy {
        SearchRvAdapter {
            searchViewModel.onBookmark(it)
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

        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.uiState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { state ->
                    onBind(state)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            searchViewModel.event.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collectLatest { event ->
                    onEvent(event)
                }
        }

        searchViewModel.onSearch("kotlin")

    }

    private fun onBind(state: SearchListUiState) {
        adapter.submitList(state.list)
    }

    private fun onEvent(event: SearchListEvent) {
        when(event) {
            is SearchListEvent.UpdateBookmark -> searchSharedViewModel.updateBookmarkItems(event.list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = SearchFragment()
    }

}