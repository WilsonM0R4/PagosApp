package com.example.pagosapp.views

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagosapp.adapters.TransactionsAdapter
import com.example.pagosapp.databinding.ActivityMainBinding
import com.example.pagosapp.viewmodels.QueryViewModel
import androidx.lifecycle.Observer
import com.example.pagosapp.interfaces.ItemClickListener
import com.example.pagosapp.models.ResponseModel
import java.util.*

class MainActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : QueryViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = QueryViewModel()
        val adapter = TransactionsAdapter()
        val intent = Intent(this, DetailActivity::class.java)
        adapter.setOnItemClickListener(object : ItemClickListener {
            override fun onItemClicked(transaction: ResponseModel) {
                intent.putExtra("transaction", transaction)
                startActivity(intent)
            }
        })
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.queryResponse.observe(this, {
            binding.searchTransactionsPB.visibility = View.GONE
            if (it.status.status == "REJECTED") {
                Toast.makeText(this, it.status.message, Toast.LENGTH_LONG).show()
            } else {
                adapter.data = it.transactions
                adapter.notifyDataSetChanged()
            }


        })

        binding.tvSearchTotal.setOnEditorActionListener { _, i, _ ->
            return@setOnEditorActionListener when (i) {
                EditorInfo.IME_ACTION_SEND -> {
                    searchTransactions()
                    true
                }
                else -> false
            }

        }

        binding.btnSearch.setOnClickListener {
            searchTransactions()
        }

        binding.floatingActionButton.setOnClickListener {
            fabClicked()
        }
    }

    private fun searchTransactions (){
        binding.searchTransactionsPB.visibility = View.VISIBLE
        viewModel.getTransactions(binding.tvSearchReference.text.toString(),
            "COP", binding.tvSearchTotal.text.toString().toInt())

    }

    private fun fabClicked() {
        val intent = Intent(this, PaymentActivity::class.java)
        startActivity(intent)
    }
}