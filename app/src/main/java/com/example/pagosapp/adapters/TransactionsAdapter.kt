package com.example.pagosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagosapp.R
import com.example.pagosapp.databinding.TransactionItemBinding
import com.example.pagosapp.interfaces.ItemClickListener
import com.example.pagosapp.models.ResponseModel

class TransactionsAdapter : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>() {

    var data : ArrayList<ResponseModel> = ArrayList()
    var mItemListener : ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.transaction_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.tvReference.text = item.reference
        holder.binding.tvDate.text = item.status.date
        holder.binding.tvMessage.text = item.status.message
        holder.binding.tvCurrency.text = item.amount.currency
        holder.binding.tvAmount.text = item.amount.total.toString()
        holder.binding.itemDataContainer.setOnClickListener {
            mItemListener?.onItemClicked(item)
        }
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    fun setOnItemClickListener (listener:ItemClickListener){
        mItemListener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = TransactionItemBinding.bind(itemView)
    }

}