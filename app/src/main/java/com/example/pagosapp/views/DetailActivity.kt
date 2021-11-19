package com.example.pagosapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pagosapp.R
import com.example.pagosapp.databinding.ActivityDetailBinding
import com.example.pagosapp.models.ResponseModel

class DetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate( layoutInflater)
        setContentView(binding.root)

        val transaction : ResponseModel = intent.getSerializableExtra("transaction")
                as ResponseModel

        binding.tvDateDet.text = String.format(getString(R.string.date_text_ph), transaction.date)
        binding.tvStatusDet.text = String.format(getString(R.string.transaction_status_text_ph),
            transaction.status.status)
        binding.tvMessageDet.text = String.format(getString(R.string.message_text_ph),
            transaction.status.message)
        binding.tvTransactionDate.text = String.format(getString(R.string.transaction_date),
            transaction.transactionDate)
        binding.tvReferenceDet.text = String.format(getString(R.string.reference_text),
            transaction.reference)
        binding.tvInternalReference.text = String.format(getString(R.string.internal_reference),
            transaction.internalReference)
        binding.tvPaymentMethod.text = String.format(getString(R.string.payment_method_ph),
            transaction.paymentMethod)
        binding.tvFranchise.text = String.format(getString(R.string.franchise),
            transaction.franchiseName)
        binding.tvIssuerName.text = String.format(getString(R.string.issuer_name),
            transaction.issuerName)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}