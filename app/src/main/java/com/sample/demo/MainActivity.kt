package com.sample.demo

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.hjq.permissions.Permission
import com.sample.demo.databinding.ActivityMainBinding
import com.sample.demo.databinding.FragmentMainBinding
import frame.basis.jitpack.BaseComponentActivity
import frame.basis.jitpack.BaseComponentFragment
import frame.basis.jitpack.BaseViewModel
import frame.basis.jitpack.extension.activity.viewBindingInflater
import frame.basis.jitpack.extension.activity.viewModel
import frame.basis.jitpack.extension.fragment.viewBinding
import frame.basis.jitpack.extension.fragment.viewModel
import frame.basis.opensource.permission.requestPermission

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
        requestPermission(listOf(Permission.ACCESS_FINE_LOCATION)) {
            granted { permissions, _ ->
                ToastUtils.showLong(permissions.toString())
            }
            denied { permissions, _ ->
                ToastUtils.showLong(permissions.toString())
            }
        }
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