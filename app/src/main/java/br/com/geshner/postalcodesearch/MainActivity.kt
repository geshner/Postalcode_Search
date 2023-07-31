package br.com.geshner.postalcodesearch

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.geshner.postalcodesearch.adapters.AddressAdapter
import br.com.geshner.postalcodesearch.databinding.ActivityMainBinding
import br.com.geshner.postalcodesearch.ui.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val adapter by lazy { AddressAdapter() }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureUi()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
               viewModel.uiState.collectLatest {
                   showProgress(it.isLoading)
                   adapter.submitList(it.addressList)
               }
            }
        }
    }

    private fun configureUi() {
        binding.button.setOnClickListener {
            it.hideKeyboard()
            viewModel.findAddress(binding.tiePostalcode.text.toString())
        }

        binding.rvAddress.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun showProgress(showProgress: Boolean) {
        binding.apply {
            progressbar.isVisible = showProgress
            rvAddress.isVisible = !showProgress
        }
    }

    private fun View.hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }
}
