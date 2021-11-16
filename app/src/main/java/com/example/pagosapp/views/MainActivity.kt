package com.example.pagosapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagosapp.R
import com.example.pagosapp.adapters.TransactionsAdapter
import com.example.pagosapp.databinding.ActivityMainBinding
import com.example.pagosapp.viewmodels.QueryViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.Observer
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val viewModel = QueryViewModel()
        val adapter = TransactionsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.queryResponse.observe(this, Observer {
            if (it.status.status == "REJECTED") {
                Toast.makeText(this, it.status.message, Toast.LENGTH_LONG).show()
            } else {
                adapter.data = it.transactions
            }


        })
        viewModel.getTransactions("ref", "COP", 125000)
        fab.setOnClickListener {
            fabClicked()
        }
    }

    fun fabClicked() {
        var intent = Intent(this, PaymentActivity::class.java)
        startActivity(intent)
    }
}