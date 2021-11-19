package com.example.pagosapp.interfaces

import com.example.pagosapp.models.CardModel

interface CardItemClickListener {
    fun onCardSelected(card : CardModel)
}
