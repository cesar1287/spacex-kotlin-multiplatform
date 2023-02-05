package com.github.cesar1287.scapexkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.github.cesar1287.scapexkmm.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //todo Finalizar design
    //todo loading
    //todo implementar com flow
    //todo Fazer iOS
    //todo koin no KMM
    //todo Hilt no Android

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getAllLaunches()
    }
}