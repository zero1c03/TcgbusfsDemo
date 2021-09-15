package com.weber.tcgbusfsdemo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.weber.tcgbusfsdemo.adapter.TcgbusfsAdapter
import com.weber.tcgbusfsdemo.databinding.FragmentTcgbusfsBinding
import com.weber.tcgbusfsdemo.viewmodels.TcgbusfsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TcgbusfsFragment : BaseFragment() {

    private lateinit var binding: FragmentTcgbusfsBinding
    private val tcgbusfsViewMocel: TcgbusfsViewModel by viewModels()
    private val mAdapter: TcgbusfsAdapter by lazy { TcgbusfsAdapter() }

    override fun onResume() {
        super.onResume()
        mainViewModel.mToolbarTitle.postValue(getString(R.string.app_name))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTcgbusfsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.mShowBackArrow.postValue(true)
        binding.rvTcg.adapter = mAdapter
        binding.rvTcg.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTcg.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.srTcg.setOnRefreshListener {
            getTcgbusfs()
        }
        getTcgbusfs()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.tcgbusfs_menu, menu)
        val timeZone: MenuItem? = menu.findItem(R.id.time_zone)
        timeZone?.setOnMenuItemClickListener {
            requireActivity().findNavController(R.id.nav_host)
                .navigate(R.id.action_view_tcgbusfs_fragment_to_view_timezone_fragment)
            true
        }
    }

    override fun onDestroy() {
        mainViewModel.mShowBackArrow.postValue(false)
        super.onDestroy()
    }

    private fun getTcgbusfs() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.mLoading.postValue(View.VISIBLE)
            tcgbusfsViewMocel.getTcgbusfs().collect {
                mAdapter.submitList(it?.news)
                mainViewModel.mLoading.postValue(View.GONE)
                binding.srTcg.isRefreshing = false
                binding.tvUpdateTime.text = getString(R.string.update_time).format(it?.updateTime)
            }
        }
    }


}