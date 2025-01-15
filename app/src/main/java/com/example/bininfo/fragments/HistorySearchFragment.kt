package com.example.bininfo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bininfo.R
import com.example.bininfo.adapter.RequestAdapter
import com.example.bininfo.databinding.FragmentHistorySearchBinding
import com.example.bininfo.viewmodel.RequestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistorySearchFragment : Fragment(R.layout.fragment_history_search) {

    private var historySearchBinding: FragmentHistorySearchBinding? = null
    private val binding get() = historySearchBinding!!

    private val requestViewModel: RequestViewModel by viewModel()
    private lateinit var requestsAdapter: RequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        historySearchBinding = FragmentHistorySearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHistoryRecyclerView()
    }

/*    private fun updateUI(request: List<Request>?) {
        if (request != null) {
            if (request.isNotEmpty()) {

            }
        }
    }*/

    private fun setupHistoryRecyclerView() {
        requestsAdapter = RequestAdapter()
        binding.historyRecyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = requestsAdapter
        }

        activity?.let {
            requestViewModel.getAllRequests().observe(viewLifecycleOwner) {request ->
                requestsAdapter.differ.submitList(request)
//                updateUI()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        historySearchBinding = null
    }

}
