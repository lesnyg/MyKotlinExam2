package com.lesnyg.mykotlinexam.bmi


import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.fragment.navArgs
import com.lesnyg.mykotlinexam.bmi.BmiResultFragmentArgs
import com.lesnyg.mykotlinexam.R
import kotlinx.android.synthetic.main.fragment_bmi_result.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class BmiResultFragment : Fragment() {
    val args: BmiResultFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bmi_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bmi = args.weight / Math.pow(args.height/100.0,2.0)
        Toast.makeText(requireContext(), "$bmi", Toast.LENGTH_SHORT).show()
        when{
            bmi >= 35 ->result_text.text = "고도비만"
            bmi >= 30 ->result_text.text = "2단계비만"
            bmi >= 25 ->result_text.text = "1단계비만"
            bmi >= 23 ->result_text.text = "과체중"
            bmi >= 18.5 ->result_text.text = "정상"
            else->result_text.text = "저체중"
        }

        //마지막 값 저장
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())

        pref.edit {
            putFloat("height",args.height)
            putFloat("weight",args.weight)
        }

//        val editer = pref.edit()
//        editer.putFloat("height",args.height)
//        .putFloat("weight",args.weight)
//            .apply()
        Log.d("BmiResultFragment","${args.height},${args.weight}")
    }

}
