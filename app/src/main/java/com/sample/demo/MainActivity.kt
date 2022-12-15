package com.sample.demo

import android.content.Intent
import android.os.Bundle
import com.sample.demo.databinding.ActivityMainBinding
import frame.basis.jitpack.BaseComponentActivity
import frame.basis.jitpack.extension.app.viewBindingInflater

class MainActivity : BaseComponentActivity() {

    private val mainViewBinding by viewBindingInflater(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBinding.btnTestView.setOnClickListener {
            startActivity(Intent(this, TestViewActivity::class.java))
        }
        mainViewBinding.btnTestDb.setOnClickListener {
            startActivity(Intent(this, TestDbActivity::class.java))
        }
    }

}