package com.example.pagosapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pagosapp.R
import com.example.pagosapp.common.Commons
import com.example.pagosapp.databinding.CardItemBinding
import com.example.pagosapp.interfaces.CardItemClickListener

class CardAdapter : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private val cards = Commons.getTestCards()
    private var mListener : CardItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.card_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.binding.tvCardNumber.text = card.number
        holder.binding.tvCardExpiration.text = card.expiration
        holder.binding.tvCardCVV.text = card.cvv
        holder.binding.tvCardInstallments.text = card.installments.toString()
        holder.binding.cardContainer.setOnClickListener {
            mListener?.onCardSelected(card)
        }
    }

    override fun getItemCount(): Int {
        return cards.count()
    }

    fun setCardItemClickListener(cardItemListener:CardItemClickListener) {
        mListener = cardItemListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CardItemBinding.bind(itemView)
    }

}