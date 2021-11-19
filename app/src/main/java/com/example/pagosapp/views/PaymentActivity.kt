package com.example.pagosapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.pagosapp.R
import com.example.pagosapp.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPaymentBinding
    private var frag = R.id.payerFragment
    private var frag2 = R.id.paymentDataFragment
    private var frag3 = R.id.selectMethodFragment
    private var frag4 = R.id.paymentResumeFragment
    private var frag5 = R.id.paymentResultFragment
    private var frag6 = R.id.mainActivity
    var frm : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        frm = frag

        binding.btnNext.setOnClickListener {
            showNextFragment()
        }

        binding.btnPrev.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showNextFragment() {

        when (frm) {
            frag -> frm = frag2
            frag2 -> frm = frag3
            frag3 -> frm = frag4
            frag4 -> {
                frm = frag5
                binding.btnNext.text = resources.getText(R.string.btn_finish)
            }
            frag5 -> frm = frag6
        }
        if (frm == frag6) {
            this.finish()
        } else {
            binding.fragmentContainerPayment.findNavController().navigate(frm)
        }

    }

    override fun onBackPressed() {

        when (frm) {
            frag5 -> {
                frm = frag4
                binding.btnNext.text = resources.getText(R.string.btn_next)
            }
            frag4 -> frm = frag3
            frag3 -> frm = frag2
            frag2 -> frm = frag
        }

        super.onBackPressed()
    }
}