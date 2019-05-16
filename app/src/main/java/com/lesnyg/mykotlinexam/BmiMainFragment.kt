package com.lesnyg.mykotlinexam


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_bmi_main.*

class BmiMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bmi_main, container, false)

        val button = view.findViewById<Button>(R.id.result_btn)
        button.setOnClickListener {
            val bundle = bundleOf(
                "height" to height_edit.text.toString().toDouble(),
                "weight" to weight_edit.text.toString().toDouble()
            )
//            bundle.putDouble("height",height_edit.text.toString().toDouble())
//            bundle.putDouble("weight",weight_edit.text.toString().toDouble())
            it.findNavController()
                .navigate(R.id.action_bmiMainFragment_to_bmiResultFragment,bundle)
        }
        return view
    }


}