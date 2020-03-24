package br.com.gabrielmarcos.mavel_app.utils.extensions

import androidx.activity.addCallback
import androidx.fragment.app.Fragment

fun Fragment.onBackPressed(action: () -> Unit) {
    requireActivity()
        .onBackPressedDispatcher
        .addCallback(this) {
            action()
        }
}

fun Fragment.onBackPressed() {
    requireActivity().onBackPressed()
}