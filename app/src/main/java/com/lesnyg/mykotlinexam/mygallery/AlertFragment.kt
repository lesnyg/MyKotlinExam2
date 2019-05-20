package com.lesnyg.mykotlinexam.mygallery


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AlertFragment(private val onClickListener: () -> Unit) : DialogFragment() {
    //    interface  OnclickListener{
//        fun onClick()
//    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("권한이 필요한 이유")
        builder.setMessage("사진 정보를 얻으려면 외부 저장소 권한이 필수입니다.")
        builder.setPositiveButton("수락", { dialog, which ->
            onClickListener.invoke()
        })
        builder.setNegativeButton("거부", null)
        return builder.create()
    }


}
