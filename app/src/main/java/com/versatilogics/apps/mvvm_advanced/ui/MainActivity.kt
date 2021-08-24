package com.versatilogics.apps.mvvm_advanced.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.versatilogics.apps.mvvm_advanced.R
import com.versatilogics.apps.mvvm_advanced.config.CONSTANTS
import com.versatilogics.apps.mvvm_advanced.databinding.ActivityMainBinding
import com.versatilogics.apps.mvvm_advanced.helpers.ext.snackbar
import com.versatilogics.apps.mvvm_advanced.network.enums.Status
import com.versatilogics.apps.mvvm_advanced.ui.adapters.VideosAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: VideosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        observers()
    }

    private fun initView() {
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.recyclerView.apply {
            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    LinearLayoutManager(this@MainActivity)
                } else {
                    GridLayoutManager(this@MainActivity, 2)
                }
            adapter = this@MainActivity.adapter
        }
    }

    private fun observers() {
        viewModel.uiVideoState.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let {
                        if (it.success == true) {
                            adapter.update(it.data.data)
                        } else {
                            binding.root.snackbar(it.message!!)
                        }
                    } ?: run {
                        binding.root.snackbar(it.message!!)
                    }
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.root.snackbar(it.message!!)
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

}