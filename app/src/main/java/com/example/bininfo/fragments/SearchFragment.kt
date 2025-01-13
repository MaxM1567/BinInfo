package com.example.bininfo.fragments

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bininfo.databinding.FragmentSearchBinding
import com.example.bininfo.viewmodel.BinViewModel

class SearchFragment : Fragment() {
    private var searchBinding: FragmentSearchBinding? = null
    private val binding get() = searchBinding!!

    private val viewModel: BinViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {

                val bin = binding.editText.text.toString()
                if (bin.isNotEmpty()) {
                    viewModel.fetchBinData(bin)
                }
                // Toast.makeText(context, binding.editText.text, Toast.LENGTH_SHORT).show()
                return@OnKeyListener true
            }
            false
        })

        viewModel.binData.observe(viewLifecycleOwner) { binInfo ->
            binding.textView.text = binInfo ?: "Error fetching data"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchBinding = null
    }
}