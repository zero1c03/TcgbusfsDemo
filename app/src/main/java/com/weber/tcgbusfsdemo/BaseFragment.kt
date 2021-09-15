package com.weber.tcgbusfsdemo

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.weber.tcgbusfsdemo.viewmodels.MainViewModel

abstract class BaseFragment : Fragment() {
    val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            requireActivity().findNavController(R.id.nav_host).popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}