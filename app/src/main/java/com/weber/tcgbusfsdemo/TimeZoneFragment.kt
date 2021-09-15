package com.weber.tcgbusfsdemo

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.weber.tcgbusfsdemo.data.Timezone
import com.weber.tcgbusfsdemo.data.Users
import com.weber.tcgbusfsdemo.databinding.FragmentTimezoneBinding
import com.weber.tcgbusfsdemo.viewmodels.TimeZoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TimeZoneFragment : BaseFragment() {

    lateinit var binding: FragmentTimezoneBinding
    private val timeZoneViewModel: TimeZoneViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        mainViewModel.mToolbarTitle.postValue(getString(R.string.time_zone))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimezoneBinding.inflate(inflater, container, false)
        viewLifecycleOwner.lifecycleScope.launch {
            val pref = requireActivity().getSharedPreferences("TCGBUSSP", MODE_PRIVATE)
            val timeZoneMap = timeZoneViewModel.getTimeZoneMenuList()
            val timeZoneItems = timeZoneViewModel.getTimeZoneMenuList().keys.toList()
            val adapter = ArrayAdapter(requireContext(), R.layout.list_timezone_item, timeZoneItems)
            var dbUser: Users? = null
            launch(Dispatchers.IO) {
                dbUser = timeZoneViewModel.getUserFromDB(mainViewModel.mUser?.username!!)
                binding.tvEmail.text = dbUser?.reportEmail
            }
            (binding.tvTimezone as? AutoCompleteTextView)?.setAdapter(adapter)
            binding.tvTimezone.setText(
                pref.getString("time_zone", adapter.getItem(0)),
                false
            )
            binding.tvTimezone.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    pref.edit().putString("time_zone", adapter.getItem(position)).apply()
                    setTimezone(dbUser, timeZoneMap[adapter.getItem(position)])
                }
        }
        return binding.root
    }

    private fun setTimezone(dbUser: Users?, timezone: String?) {
        if (dbUser != null && timezone != null) {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                val user =
                    timeZoneViewModel.updateUser(
                        dbUser.objectId,
                        dbUser.sessionToken,
                        Timezone(timezone.toInt())
                    )
                user.collect {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.update_success),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
