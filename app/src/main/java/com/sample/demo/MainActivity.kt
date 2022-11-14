package com.sample.demo

import android.os.Bundle
import android.view.View
import com.sample.demo.databinding.ActivityMainBinding
import com.sample.demo.databinding.FragmentMainBinding
import frame.basis.jitpack.BaseComponentActivity
import frame.basis.jitpack.BaseComponentFragment
import frame.basis.jitpack.BaseViewModel
import frame.basis.jitpack.extension.app.viewBinding
import frame.basis.jitpack.extension.app.viewBindingInflater
import frame.basis.jitpack.extension.app.viewModel

class MainActivity : BaseComponentActivity() {

    class MainViewModel : BaseViewModel()

    private val mainViewModel by viewModel<MainViewModel>()
    private val mainViewBinding by viewBindingInflater(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBinding.tv.text = mainViewModel.toString()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment, MainFragment())
            .commitAllowingStateLoss()
    }

    class MainFragment : BaseComponentFragment(R.layout.fragment_main) {

        class MainViewModel : BaseViewModel()

        private val mainViewModel: MainViewModel by viewModel()
        private val viewBind by viewBinding(FragmentMainBinding::bind)

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            viewBind.tv.text = mainViewModel.toString()
        }

    }

}