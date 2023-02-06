package com.github.cesar1287.scapexkmm.android.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cesar1287.scapexkmm.android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

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

        viewModel.command.observe(this) {
            when(it) {
                is Command.Loading -> {
                    binding.pbMainLoading.isVisible = it.value
                }
                is Command.Error -> {
                    with(binding) {
                        rvMainLaunches.isVisible = false
                        tvMainError.isVisible = true
                        pbMainLoading.isVisible = false
                    }
                }
            }
        }
    }
}