package com.lesnyg.mykotlinexam

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment

//확장함수

fun Context.toast(text: String) {
    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(text: String) {
    Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()
}

fun Any.logd(text: String) {
    Log.d(this::class.java.simpleName, text)
}