package com.example.pagosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagosapp.R
import com.example.pagosapp.databinding.TransactionItemBinding
import com.example.pagosapp.models.ResponseModel

class TransactionsAdapter : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    var data : ArrayList<ResponseModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.transaction_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.tvReason.text = item.status.reason
        holder.binding.tvDate.text = item.status.date
        holder.binding.tvMessage.text = item.status.message
    }

    override fun getItemCount(): Int {
        return data.count()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = TransactionItemBinding.bind(itemView)
    }

}