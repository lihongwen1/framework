package com.sample.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.demo.databinding.ActivityTestViewBinding
import frame.basis.jitpack.extension.app.viewBindingInflater
import frame.basis.jitpack.extension.view.noEmojiInputExpand

class TestViewActivity : AppCompatActivity(R.layout.activity_test_view) {

    private val mainViewBinding by viewBindingInflater(ActivityTestViewBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBinding.testNoEmojiView.noEmojiInputExpand()
    }

}