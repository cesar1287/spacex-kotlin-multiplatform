package com.github.cesar1287.scapexkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cesar1287.scapexkmm.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //todo Fazer iOS
    //todo loading
    //todo Hilt no Android
    //todo koin no KMM
    //todo implementar com flow

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private val mainAdapter by lazy {
        MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getAllLaunches()
        setupViews()
    }

    private fun setupViews() {
        binding.rvMainLaunches.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

        viewModel.onLaunchesList.observe(this) {
            mainAdapter.submitList(it)
        }
    }
}