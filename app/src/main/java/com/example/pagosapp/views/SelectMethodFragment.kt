package com.example.pagosapp.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagosapp.R
import com.example.pagosapp.adapters.CardAdapter
import com.example.pagosapp.common.Commons
import com.example.pagosapp.common.SharedData
import com.example.pagosapp.databinding.FragmentSelectMethodBinding
import com.example.pagosapp.interfaces.CardItemClickListener
import com.example.pagosapp.models.CardModel
import com.example.pagosapp.models.InstrumentModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectMethodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectMethodFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding : FragmentSelectMethodBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSelectMethodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CardAdapter()
        adapter.setCardItemClickListener(object:CardItemClickListener{
            override fun onCardSelected(card : CardModel) {
                SharedData.instrumentModel = InstrumentModel(card)
                Toast.makeText(context, "Tarjeta seleccionada", Toast.LENGTH_SHORT).show()
            }
        })
        binding.rvCards.layoutManager = LinearLayoutManager(context)
        binding.rvCards.adapter = adapter
    }

    override fun onPause() {
        super.onPause()
        if (SharedData.instrumentModel == null) {
            SharedData.instrumentModel = InstrumentModel(Commons.getTestCards()[0])
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SelectMethodFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectMethodFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}