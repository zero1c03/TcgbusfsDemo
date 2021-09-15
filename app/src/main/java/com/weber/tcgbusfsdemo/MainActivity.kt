package com.weber.tcgbusfsdemo

import android.os.Bundle
import androidx.activity.viewModels
import com.weber.tcgbusfsdemo.databinding.ActivityMainBinding
import com.weber.tcgbusfsdemo.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.mtToolbar)
        setContentView(binding.root)

        mainViewModel.run {
            mLoading.observe(this@MainActivity, {
                setLoading(it)
            })

            mShowBackArrow.observe(this@MainActivity, {
                supportActionBar?.setDisplayHomeAsUpEnabled(it);
                supportActionBar?.setDisplayShowHomeEnabled(it);
            })

            mToolbarTitle.observe(this@MainActivity, {
                binding.mtToolbar.title = it
            })

        }
    }


    private fun setLoading(visible: Int) {
        setLoading(visible, binding.root)
    }

}