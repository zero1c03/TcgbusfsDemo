package com.weber.tcgbusfsdemo

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.weber.tcgbusfsdemo.databinding.FragmentLoginBinding
import com.weber.tcgbusfsdemo.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        mainViewModel.mToolbarTitle.postValue(getString(R.string.app_name))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mtLogin.setOnClickListener {
            it.requestFocus()
            // Test
//            binding.teEmail.setText(account)
//            binding.tePassword.setText(password)

            viewLifecycleOwner.lifecycleScope.launch {
                login(
                    binding.teEmail.text.toString().trim(),
                    binding.tePassword.text.toString()
                )
            }
        }
    }

    private suspend fun login(id: String, password: String) {
        viewLifecycleOwner.lifecycleScope.runCatching {
            mainViewModel.mLoading.postValue(View.VISIBLE)
            if (checkValid(id, password)) {
                loginViewModel.getUserInfo(id, password).collect {
                    if (it != null) {
                        launch(Dispatchers.IO) {
                            loginViewModel.setUserInfo(it)
                            mainViewModel.mUser = it
                            launch(Dispatchers.Main) {
                                Snackbar.make(
                                    binding.root,
                                    getString(R.string.login_success),
                                    Snackbar.LENGTH_SHORT
                                ).show()
                                requireActivity().findNavController(R.id.nav_host)
                                    .navigate(R.id.action_view_pager_fragment_to_view_tcgbusfs_fragment)
                            }

                        }
                    } else {
                        Snackbar.make(
                            binding.root,
                            getString(R.string.login_fail),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Snackbar.make(binding.root, getString(R.string.login_fail), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }.onSuccess {
            mainViewModel.mLoading.postValue(View.GONE)
        }.onFailure {
            mainViewModel.mLoading.postValue(View.GONE)
            Snackbar.make(
                binding.root,
                getString(R.string.login_error),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    private fun checkValid(id: String, password: String): Boolean {
        if (id.isEmpty() || !id.isValidEmail()) {
            binding.tlEmail.error = getString(R.string.invalid_account)
        } else {
            binding.tlEmail.error = null
        }

        if (password.isEmpty()) {
            binding.tlPassword.error = getString(R.string.invalid_password)
        } else {
            binding.tlPassword.error = null
        }

        return binding.tlEmail.error == null && binding.tlPassword.error == null
    }

    private fun CharSequence?.isValidEmail() =
        !isNullOrBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    companion object {
        const val account = "test2@qq.com"
        const val password = "test1234qq"
    }

}