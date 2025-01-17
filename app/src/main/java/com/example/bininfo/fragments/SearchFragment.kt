package com.example.bininfo.fragments

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bininfo.R
import com.example.bininfo.databinding.FragmentSearchBinding
import com.example.bininfo.model.Request
import com.example.bininfo.viewmodel.BinViewModel
import com.example.bininfo.viewmodel.RequestViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var searchBinding: FragmentSearchBinding? = null
    private val binding get() = searchBinding!!

    private val binViewModel: BinViewModel by viewModel()
    private val requestViewModel: RequestViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Поиск с клавиатуры
        binding.editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                startSearch()
                return@OnKeyListener true
            }
            false
        })
        // Поиск с кнопки
        binding.searchButton.setOnClickListener { startSearch() }
        // Наблюдатель
        binViewModel.binData.observe(viewLifecycleOwner) { binInfo ->
            try {
                val type = object : TypeToken<Map<String, Any?>>() {}.type
                val parsedData: Map<String, Any?> = Gson().fromJson(binInfo, type)

                val bin = parsedData["bin"] as? String ?: "Unknown"
                val scheme = parsedData["scheme"] as? String
                val typeStr = parsedData["type"] as? String

                val bankMap = parsedData["bank"] as? Map<String, Any?>
                val bankName = bankMap?.get("name") as? String

                val countryMap = parsedData["country"] as? Map<String, Any?>
                val countryName = countryMap?.get("name") as? String
                val countryCurrency = countryMap?.get("currency") as? String

                binding.binNumber.text = "BIN: $bin"
                binding.generalData.text = "Счёт: $scheme; $typeStr"
                binding.countryData.text = "Страна: $countryName; $countryCurrency"
                binding.bankData.text = "Банк: $bankName"

                saveRequest(parsedData)
            } catch (e: Exception) {
                Log.d("ERR", binInfo.toString())
                binding.binNumber.text = binInfo.toString()
            }
        }
    }

    private fun startSearch() {
        val bin = binding.editText.text.toString()
        if (inputValidation(bin)) {
            binViewModel.fetchBinData(bin)
        }
    }

    private fun inputValidation(bin: String): Boolean {
        if (bin.isEmpty()) {
            Toast.makeText(context, "Введите BIN номер!", Toast.LENGTH_SHORT).show()
            return false
        } else if (bin.length !in 6..8) {
            Toast.makeText(context, "Введите 6-8 цифр!", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveRequest(parsedData: Map<String, Any?>) {
        try {
            val bin = parsedData["bin"] as? String ?: "Unknown"
            val scheme = parsedData["scheme"] as? String
            val typeStr = parsedData["type"] as? String

            val bankMap = parsedData["bank"] as? Map<String, Any?>
            val bankName = bankMap?.get("name") as? String

            val countryMap = parsedData["country"] as? Map<String, Any?>
            val countryName = countryMap?.get("name") as? String
            val countryCurrency = countryMap?.get("currency") as? String

            val request = Request(
                id = 0,
                bin = bin,
                scheme = scheme,
                type = typeStr,
                bankName = bankName,
                countryName = countryName,
                countryCurrency = countryCurrency
            )

            requestViewModel.addRequest(request)
        } catch (e: Exception) {
            Log.e("ERR", "Error parsing or saving request: ${e.message}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        searchBinding = null
    }
}