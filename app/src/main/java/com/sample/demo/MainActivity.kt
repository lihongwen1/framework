package com.sample.demo

import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.hjq.permissions.Permission
import com.sample.demo.databinding.ActivityMainBinding
import com.sample.demo.databinding.FragmentMainBinding
import frame.basis.base.BaseComponentActivity
import frame.basis.base.BaseComponentFragment
import frame.basis.base.BaseViewModel
import frame.basis.extension.viewBinding
import frame.basis.extension.viewBindingInflater
import frame.basis.extension.viewModel
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

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            viewBind.tv.text = mainViewModel.toString()
        }

    }

}