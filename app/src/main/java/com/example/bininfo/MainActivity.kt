package com.example.bininfo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bininfo.database.RequestDatabase
import com.example.bininfo.databinding.ActivityMainBinding
import com.example.bininfo.fragments.HistorySearchFragment
import com.example.bininfo.fragments.SearchFragment
import com.example.bininfo.repository.RequestRepository
import com.example.bininfo.viewmodel.RequestViewModel
import com.example.bininfo.viewmodel.RequestViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var requestViewModel: RequestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        replaceFragment(SearchFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.search -> replaceFragment(SearchFragment())
                R.id.history -> replaceFragment(HistorySearchFragment())
                else -> {}
            }
            true
        }

        setupViewModel()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(binding.frameContainer.id, fragment)
        fragmentTransaction.commit()
    }

    private fun setupViewModel() {
        val requestRepository = RequestRepository(RequestDatabase(this))
        val viewModelProviderFactory = RequestViewModelFactory(application, requestRepository)
        requestViewModel = ViewModelProvider(this, viewModelProviderFactory)[RequestViewModel::class.java]
    }
}