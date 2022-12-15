package com.sample.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sample.demo.databinding.ActivityTestDbBinding
import frame.basis.jitpack.extension.app.viewBindingInflater
import frame.library.db.RoomProvider
import frame.library.db.TestDb
import kotlinx.coroutines.launch

class TestDbActivity : AppCompatActivity(R.layout.activity_test_db) {

    private val testDbBinding by viewBindingInflater(ActivityTestDbBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testDbBinding.btnInsertDb.setOnClickListener {
            lifecycleScope.launch {
                val allTestAd = RoomProvider.getAllTestAd()
                RoomProvider.insertTestDao(TestDb(value = ("insert ${allTestAd.size + 1}").toString()))
                refreshDb()
            }
        }
        refreshDb()
    }

    private fun refreshDb() {
        lifecycleScope.launch {
            val allTestAd = RoomProvider.getAllTestAd().reversed().joinToString("\n") { it.value }
            testDbBinding.textDbData.text = allTestAd
        }
    }

}