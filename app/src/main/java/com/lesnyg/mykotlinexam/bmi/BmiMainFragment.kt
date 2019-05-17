package com.lesnyg.mykotlinexam.bmi


import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.lesnyg.mykotlinexam.bmi.BmiMainFragmentDirections
import com.lesnyg.mykotlinexam.R
import kotlinx.android.synthetic.main.fragment_bmi_main.*

class BmiMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bmi_main, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val height = pref.getFloat("height",0f)
        val weight = pref.getFloat("weight",0f)
        if(height != 0f &&  weight !=0f){
            height_edit.setText(height.toString())
            weight_edit.setText(weight.toString())
        }
        val button = view.findViewById<Button>(R.id.result_btn)
        button.setOnClickListener {
            val action =
                BmiMainFragmentDirections.actionBmiMainFragmentToBmiResultFragment(
                    height_edit.text.toString().toFloat(),
                    weight_edit.text.toString().toFloat()
                )


//            bundle.putDouble("height",height_edit.text.toString().toDouble())
//            bundle.putDouble("weight",weight_edit.text.toString().toDouble())
            it.findNavController()
                .navigate(action)
        }
    }


}
