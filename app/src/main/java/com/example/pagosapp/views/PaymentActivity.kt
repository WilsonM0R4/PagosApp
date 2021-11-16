package com.example.pagosapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import com.example.pagosapp.R
import com.example.pagosapp.databinding.ActivityPaymentBinding
import com.example.pagosapp.models.*
import com.example.pagosapp.viewmodels.PaymentViewModel

class PaymentActivity : AppCompatActivity() {

    private lateinit var fragmentContainer : FragmentContainerView
    private lateinit var btnNext : Button
    private lateinit var btnPrev : Button
    lateinit var frm : Fragment
    private var frag = PayerFragment.newInstance("", "")
    private var frag2 = PaymentDataFragment.newInstance("", "")
    private var frag3 = SelectMethodFragment.newInstance("", "")
    private var frag4 = PaymentResumeFragment.newInstance("", "")
    private var frag5 = PaymentResultFragment.newInstance("", "")
    private lateinit var binding : ActivityPaymentBinding
    lateinit var payerModel : PayerModel
    lateinit var paymentModel : PaymentModel
    lateinit var instrumentModel: InstrumentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentContainer = findViewById(R.id.fragmentContainerPayment)
        frm = frag

        binding.btnNext.setOnClickListener {
            showNextFragment()
            /*if (frm==frag4) {
                showNextFragment()
                makePayment()
            } else {
                showNextFragment()
            }*/
        }

        binding.btnPrev.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showNextFragment() {

        when (frm) {
            frag -> { //payerData()
                frm = frag2}
            frag2 -> {//paymentData()
                frm = frag3}
            frag3 -> {//methodData()
                frm = frag4}
            frag4 -> frm = frag5
        }

        val frMng = supportFragmentManager
        frMng.beginTransaction().replace(R.id.fragmentContainerPayment,frm)
            .addToBackStack(null).commit()

    }

    private fun makePayment(){
        Toast.makeText(this, "Making payment", Toast.LENGTH_SHORT).show()
    }

    private fun payerData(){
        val name = frag.binding.etPayerName.text.toString()
        val surname = frag.binding.etPayerSurname.text.toString()
        val email = frag.binding.etPayerEmail.text.toString()
        val docType = frag.binding.tvDocType.text.toString()
        val document = frag.binding.etPayerDocument.text.toString()
        payerModel = PayerModel(name, surname, email, docType, document, "")
    }

    private fun paymentData() {
        val reference = frag2.binding.etPaymentReference.text.toString()
        val description = frag2.binding.etPaymentDescription.text.toString()
        val currency = frag2.binding.etPaymentCurrency.text.toString()
        val total = frag2.binding.etPaymentTotal.text.toString().toInt()
        val amountModel = AmountModel(currency, total)
        paymentModel = PaymentModel(reference, description, amountModel)
    }

    private fun methodData() {
        val cardNumber = frag3.binding.tvCardNumber.toString()
        val cardExpiration = frag3.binding.tvCardExpiration.toString()
        val cardCVV = frag3.binding.tvCardCVV.toString()
        val installments = frag3.binding.tvCardInstallments.toString().toInt()
        val cardModel = CardModel(cardNumber, cardExpiration, cardCVV, installments)
        instrumentModel = InstrumentModel(cardModel)
    }

    override fun onBackPressed() {

        when (frm) {
            frag4-> frm = frag3
            frag3 -> frm = frag2
            frag2 -> frm = frag
        }

        super.onBackPressed()
    }
}